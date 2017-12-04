package org.amv.access.sdk.hm.crypto;

import com.highmobility.crypto.HMKeyPair;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

public class HmKeys implements Keys {
    private final HMKeyPair keyPair;

    public HmKeys(HMKeyPair keyPair) {
        this.keyPair = checkNotNull(keyPair);
    }

    @Override
    public byte[] getPublicKey() {
        return Arrays.copyOf(keyPair.getPublicKey(), keyPair.getPublicKey().length);
    }

    @Override
    public byte[] getPrivateKey() {
        return Arrays.copyOf(keyPair.getPrivateKey(), keyPair.getPrivateKey().length);
    }
}
