package org.amv.access.sdk.hm.secure;

public interface Codec {
    String encryptData(String unencryptedData);

    String decryptData(String encryptedData);
}