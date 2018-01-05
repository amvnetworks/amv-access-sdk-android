package org.amv.access.sdk.hm.secure;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import com.google.common.base.Charsets;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;

import lombok.Builder;
import lombok.Value;

import static com.google.common.base.Preconditions.checkNotNull;

/*
MIT License: https://opensource.org/licenses/MIT
Copyright 2017 Diederik Hattingh
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
/*
   Only applicable for Android >= 6.0
*/
public class AndroidCodec implements Codec {
    @Value
    @Builder
    public static class Options {
        private String keyAlias;
        private String encryptedKeyName;
        private byte[] initVector;
        private String aesMode;
        private int aesKeyLength;
    }

    private static final String ANDROID_KEY_STORE_NAME = "AndroidKeyStore";

    private final Options options;

    public AndroidCodec(Options options) {
        this.options = checkNotNull(options);
    }

    @Override
    public String encryptData(String stringDataToEncrypt) {
        try {
            return encryptDataInternal(stringDataToEncrypt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decryptData(String encryptedData) {
        try {
            return decryptDataInternal(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encryptDataInternal(String stringDataToEncrypt) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableEntryException, NoSuchProviderException, InvalidAlgorithmParameterException, IOException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        initKeys();

        if (stringDataToEncrypt == null) {
            throw new IllegalArgumentException("Data to be decrypted must be non null");
        }

        Cipher cipher = Cipher.getInstance(options.getAesMode());
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), new GCMParameterSpec(options.getAesKeyLength(), options.getInitVector()));

        byte[] encodedBytes = cipher.doFinal(stringDataToEncrypt.getBytes(Charsets.UTF_8));
        return Base64.encodeToString(encodedBytes, Base64.DEFAULT);
    }

    private String decryptDataInternal(String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, UnrecoverableEntryException, CertificateException, KeyStoreException, IOException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchProviderException, BadPaddingException, IllegalBlockSizeException {

        initKeys();

        if (encryptedData == null) {
            throw new IllegalArgumentException("Data to be decrypted must be non null");
        }

        byte[] encryptedDecodedData = Base64.decode(encryptedData, Base64.DEFAULT);

        Cipher c;
        try {
            c = Cipher.getInstance(options.getAesMode());
            c.init(Cipher.DECRYPT_MODE, getSecretKey(), new GCMParameterSpec(options.getAesKeyLength(), options.getInitVector()));
        } catch (InvalidKeyException | IOException e) {
            // Since the keys can become bad (perhaps because of lock screen change)
            // drop keys in this case.
            removeKeys();
            throw e;
        }

        byte[] decodedBytes = c.doFinal(encryptedDecodedData);
        return new String(decodedBytes, Charsets.UTF_8);
    }

    // Using algorithm as described at https://medium.com/@ericfu/securely-storing-secrets-in-an-android-application-501f030ae5a3
    private void initKeys() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException, UnrecoverableEntryException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        keyStore.load(null);

        if (!keyStore.containsAlias(options.getKeyAlias())) {
            generateValidKeys();
        } else {
            boolean keyValid = false;
            KeyStore.Entry keyEntry = keyStore.getEntry(options.getKeyAlias(), null);

            if (keyEntry instanceof KeyStore.SecretKeyEntry) {
                keyValid = true;
            }

            if (!keyValid) {
                // System upgrade or something made key invalid
                removeKeys(keyStore);
                generateValidKeys();
            }
        }
    }

    private void removeKeys() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
            keyStore.load(null);
            removeKeys(keyStore);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void removeKeys(KeyStore keyStore) {
        try {
            keyStore.deleteEntry(options.getKeyAlias());
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateValidKeys() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(options.getKeyAlias(),
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                // NOTE no Random IV. According to above this is less secure but acceptably so.
                .setRandomizedEncryptionRequired(false)
                .build();

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE_NAME);
        keyGenerator.init(keyGenParameterSpec);

        // Note according to [docs](https://developer.android.com/reference/android/security/keystore/KeyGenParameterSpec.html)
        // this generation will also add it to the keystore.
        keyGenerator.generateKey();
    }

    private Key getSecretKey() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE_NAME);
        keyStore.load(null);
        Key key = keyStore.getKey(options.getKeyAlias(), null);
        if (key == null) {
            String errorMessage = String.format("key `%s` not found in key store",
                    options.getEncryptedKeyName());
            throw new IllegalStateException(errorMessage);
        }
        return key;

    }
}