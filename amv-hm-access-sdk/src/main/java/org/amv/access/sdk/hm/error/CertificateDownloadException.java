package org.amv.access.sdk.hm.error;

import org.amv.access.sdk.spi.error.AccessSdkException;

public class CertificateDownloadException extends AccessSdkException {
    private static Type TYPE = new Type() {
        @Override
        public String getName() {
            return "CERT_DOWNLOAD_FAILED";
        }

        @Override
        public String getDescription() {
            return "There was an error while downloading certificates.";
        }
    };

    public CertificateDownloadException(Throwable cause) {
        super(TYPE, cause);
    }
}
