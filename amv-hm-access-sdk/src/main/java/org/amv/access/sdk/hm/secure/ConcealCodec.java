package org.amv.access.sdk.hm.secure;

import android.util.Base64;

import com.facebook.crypto.Crypto;
import com.facebook.crypto.Entity;
import com.google.common.base.Charsets;

import static com.google.common.base.Preconditions.checkNotNull;


public class ConcealCodec implements Codec {

    private final Crypto crypto;

    public ConcealCodec(Crypto crypto) {
        this.crypto = checkNotNull(crypto);

        if (!crypto.isAvailable()) {
            throw new IllegalStateException("Crypto is not available. Maybe Android could not load libraries correctly.");
        }
    }

    @Override
    public String encryptData(String key, String value) {
        try {
            byte[] encryptedData = crypto.encrypt(value.getBytes(Charsets.UTF_8), Entity.create(key));
            return Base64.encodeToString(encryptedData, Base64.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decryptData(String key, String value) {
        try {
            byte[] encryptedDecodedData = Base64.decode(value, Base64.DEFAULT);
            byte[] plainText = crypto.decrypt(encryptedDecodedData, Entity.create(key));
            return new String(plainText, Charsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}