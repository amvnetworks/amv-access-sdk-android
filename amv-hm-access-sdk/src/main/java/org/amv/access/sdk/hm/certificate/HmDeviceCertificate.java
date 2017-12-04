package org.amv.access.sdk.hm.certificate;

import com.highmobility.utils.Bytes;

import org.amv.access.sdk.spi.certificate.DeviceCertificate;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

class HmDeviceCertificate implements DeviceCertificate {

    private final com.highmobility.crypto.DeviceCertificate deviceCertificate;

    HmDeviceCertificate(com.highmobility.crypto.DeviceCertificate deviceCertificate) {
        this.deviceCertificate = checkNotNull(deviceCertificate);
    }

    @Override
    public byte[] toByteArray() {
        return Arrays.copyOf(deviceCertificate.getBytes(),
                deviceCertificate.getBytes().length);
    }

    @Override
    public String getDeviceSerial() {
        return Bytes.hexFromBytes(deviceCertificate.getSerial());
    }
}
