package org.amv.access.sdk.hm.secure;

public interface Codec {
    String encryptData(String key, String unencryptedData);

    String decryptData(String key, String encryptedData);
}