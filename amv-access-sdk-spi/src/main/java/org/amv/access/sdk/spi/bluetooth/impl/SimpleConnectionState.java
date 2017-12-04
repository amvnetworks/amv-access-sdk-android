package org.amv.access.sdk.spi.bluetooth.impl;

import org.amv.access.sdk.spi.bluetooth.ConnectionState;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SimpleConnectionState implements ConnectionState {
    private final boolean connected;
    private final boolean authenticated;
}
