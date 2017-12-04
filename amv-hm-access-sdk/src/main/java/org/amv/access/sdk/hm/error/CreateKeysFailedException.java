package org.amv.access.sdk.hm.error;

import org.amv.access.sdk.spi.error.AccessSdkException;

public class CreateKeysFailedException extends AccessSdkException {
    private static AccessSdkException.Type TYPE = new Type() {
        @Override
        public String getName() {
            return "CREATE_KEYS_FAILED";
        }

        @Override
        public String getDescription() {
            return "Something went wrong during key generation. " +
                    "See downstream exception for more information.";
        }
    };

    public CreateKeysFailedException(Throwable cause) {
        super(TYPE, cause);
    }
}
