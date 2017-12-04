package org.amv.access.sdk.hm.certificate;

import com.google.gson.annotations.SerializedName;
import com.highmobility.utils.Base64;

import org.amv.access.sdk.spi.certificate.AccessCertificatePair;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class SerializableAccessCertificatePair {
    static SerializableAccessCertificatePair from(AccessCertificatePair accessCertificatePair) {
        String deviceAccessCertificateBase64 = Base64.encode(accessCertificatePair
                .getDeviceAccessCertificate()
                .toByteArray());
        String vehicleAccessCertificateBase64 = Base64.encode(accessCertificatePair
                .getVehicleAccessCertificate()
                .toByteArray());

        SerializableAccessCertificatePair val = new SerializableAccessCertificatePair();
        val.setId(accessCertificatePair.getId());
        val.setDeviceAccessCertificate(deviceAccessCertificateBase64);
        val.setVehicleAccessCertificate(vehicleAccessCertificateBase64);

        return val;
    }

    @SerializedName("id")
    private String id;
    @SerializedName("device_access_certificate")
    private String deviceAccessCertificate;
    @SerializedName("vehicle_access_certificate")
    private String vehicleAccessCertificate;
}
