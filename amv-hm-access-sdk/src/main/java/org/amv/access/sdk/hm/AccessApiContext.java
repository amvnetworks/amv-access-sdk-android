package org.amv.access.sdk.hm;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AccessApiContext {
    @NonNull
    private final String baseUrl;
    @NonNull
    private final String apiKey;
    @NonNull
    private final String appId;
}
