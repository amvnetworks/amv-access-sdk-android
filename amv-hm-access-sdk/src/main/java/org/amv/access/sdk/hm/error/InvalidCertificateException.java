package org.amv.access.sdk.hm.error;

import org.amv.access.sdk.spi.error.AccessSdkException;

public class InvalidCertificateException extends AccessSdkException {
    private static Type TYPE = new Type() {
        @Override
        public String getName() {
            return "INVALID_CERTIFICATE";
        }

        @Override
        public String getDescription() {
            return "The given certificate is invalid and has not been accepted.";
        }
    };

    public InvalidCertificateException(Throwable cause) {
        super(TYPE, cause);
    }
}
