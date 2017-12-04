package org.amv.access.sdk.spi.bluetooth.impl;

import org.amv.access.sdk.spi.bluetooth.BroadcastState;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SimpleBroadcastState implements BroadcastState {
    private final boolean bluetoothEnabled;
    private final boolean idle;
    private final boolean broadcasting;
}
