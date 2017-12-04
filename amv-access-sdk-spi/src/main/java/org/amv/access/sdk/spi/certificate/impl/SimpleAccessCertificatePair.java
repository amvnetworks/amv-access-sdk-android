package org.amv.access.sdk.spi.certificate.impl;

import org.amv.access.sdk.spi.certificate.AccessCertificate;
import org.amv.access.sdk.spi.certificate.AccessCertificatePair;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class SimpleAccessCertificatePair implements AccessCertificatePair {
    @NonNull
    private final String id;
    @NonNull
    private final AccessCertificate deviceAccessCertificate;
    @NonNull
    private final AccessCertificate vehicleAccessCertificate;
}
