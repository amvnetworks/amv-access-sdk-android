package org.amv.access.sdk.hm.certificate;

import org.amv.access.sdk.spi.certificate.DeviceCertificate;

public interface DeviceCertificateWithIssuerKey {
    DeviceCertificate getDeviceCertificate();

    byte[] getIssuerPublicKey();
}
