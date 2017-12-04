package org.amv.access.sdk.hm.certificate;

import org.amv.access.sdk.spi.certificate.DeviceCertificate;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

class HmDeviceCertificateWithIssuerKey implements DeviceCertificateWithIssuerKey {

    private final DeviceCertificate deviceCertificate;
    private final byte[] issuerPublicKey;

    HmDeviceCertificateWithIssuerKey(DeviceCertificate deviceCertificate,
                                     byte[] issuerPublicKey) {
        this.deviceCertificate = checkNotNull(deviceCertificate);
        this.issuerPublicKey = Arrays.copyOf(issuerPublicKey, issuerPublicKey.length);
    }

    @Override
    public DeviceCertificate getDeviceCertificate() {
        return deviceCertificate;
    }

    @Override
    public byte[] getIssuerPublicKey() {
        return Arrays.copyOf(issuerPublicKey, issuerPublicKey.length);
    }
}
