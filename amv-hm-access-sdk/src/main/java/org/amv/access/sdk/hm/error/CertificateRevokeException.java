package org.amv.access.sdk.hm.error;

import org.amv.access.sdk.spi.error.AccessSdkException;

public class CertificateRevokeException extends AccessSdkException {
    private static Type TYPE = new Type() {
        @Override
        public String getName() {
            return "CERT_REVOKE_FAILED";
        }

        @Override
        public String getDescription() {
            return "The given certificate could not be revoked.";
        }
    };

    public CertificateRevokeException(Throwable cause) {
        super(TYPE, cause);
    }
}
