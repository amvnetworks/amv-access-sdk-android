package org.amv.access.sdk.spi.certificate;

import java.util.Calendar;

/**
 * An interface representing an access certificate.
 */
public interface AccessCertificate {

    byte[] toByteArray();

    String getProviderSerial();

    String getGainerSerial();

    Calendar getStartDate();

    Calendar getEndDate();

    boolean isExpired();

    boolean isNotValidYet();

    default boolean isValidNow() {
        return !isExpired() && !isNotValidYet();
    }
}
