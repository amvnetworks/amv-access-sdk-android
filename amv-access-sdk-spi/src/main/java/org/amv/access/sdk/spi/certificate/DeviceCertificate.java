package org.amv.access.sdk.spi.certificate;

/**
 * An interface representing a device certificate.
 */
public interface DeviceCertificate {
    byte[] toByteArray();

    String getDeviceSerial();

    //byte[] getIssuer();
    //byte[] getAppIdentifier();
    //byte[] getPublicKey();
    //byte[] getCertificateData();
    //byte[] getSignature();
}
