package org.amv.access.sdk.spi.certificate;

/**
 * An interface representing an access certificate pair.
 * <p>
 * One access certificate is for the device.
 * The other certificate is for the module in the vehicle.
 * <p>
 * This interface provides an id which can be used to refer
 * to the certificate pair on a remote server, e.g. when revoking
 * the certificates.
 */
public interface AccessCertificatePair {
    String getId();

    AccessCertificate getDeviceAccessCertificate();

    AccessCertificate getVehicleAccessCertificate();
}
