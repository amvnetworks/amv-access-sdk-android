package org.amv.access.sdk.hm.certificate;

import com.highmobility.crypto.AccessCertificate;

import org.amv.access.client.model.java6.AccessCertificateDto;
import org.amv.access.sdk.spi.certificate.AccessCertificatePair;
import org.amv.access.sdk.spi.certificate.impl.SimpleAccessCertificatePair;

final class HmAccessCertificatePairs {

    static AccessCertificatePair create(SerializableAccessCertificatePair accessCertificate) {
        HmAccessCertificate deviceCert = fromBase64OrThrow(accessCertificate.getDeviceAccessCertificate());
        HmAccessCertificate vehicleCert = fromBase64OrThrow(accessCertificate.getVehicleAccessCertificate());

        return SimpleAccessCertificatePair.builder()
                .id(accessCertificate.getId())
                .deviceAccessCertificate(deviceCert)
                .vehicleAccessCertificate(vehicleCert)
                .build();
    }

    static AccessCertificatePair create(AccessCertificateDto accessCertificateDto) {
        HmAccessCertificate deviceCert = fromBase64OrThrow(accessCertificateDto.device_access_certificate);
        HmAccessCertificate vehicleCert = fromBase64OrThrow(accessCertificateDto.vehicle_access_certificate);

        return SimpleAccessCertificatePair.builder()
                .id(accessCertificateDto.id)
                .deviceAccessCertificate(deviceCert)
                .vehicleAccessCertificate(vehicleCert)
                .build();
    }

    private static HmAccessCertificate fromBase64OrThrow(String accessCertificateBase64) {
        try {
            return new HmAccessCertificate(new AccessCertificate(accessCertificateBase64));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private HmAccessCertificatePairs() {
        throw new UnsupportedOperationException();
    }
}
