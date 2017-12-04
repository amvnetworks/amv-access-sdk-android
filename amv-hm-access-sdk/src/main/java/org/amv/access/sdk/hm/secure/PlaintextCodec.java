package org.amv.access.sdk.hm.secure;

/**
 * Created by alei2 on 02.12.2017.
 */
public class PlaintextCodec implements Codec {
    @Override
    public String encryptData(String unencryptedData) {
        return unencryptedData;
    }

    @Override
    public String decryptData(String encryptedData) {
        return encryptedData;
    }
}
