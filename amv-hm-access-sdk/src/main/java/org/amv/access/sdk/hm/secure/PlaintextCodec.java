package org.amv.access.sdk.hm.secure;

public class PlaintextCodec implements Codec {
    @Override
    public String encryptData(String key, String unencryptedData) {
        return unencryptedData;
    }

    @Override
    public String decryptData(String key, String encryptedData) {
        return encryptedData;
    }
}
