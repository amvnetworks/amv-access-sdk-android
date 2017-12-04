package org.amv.access.sdk.spi.bluetooth.impl;

import com.google.common.base.Optional;

import org.amv.access.sdk.spi.bluetooth.BroadcastState;
import org.amv.access.sdk.spi.bluetooth.BroadcastStateChangeEvent;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class SimpleBroadcastStateChangeEvent implements BroadcastStateChangeEvent {
    @NonNull
    private final BroadcastState currentState;
    private final BroadcastState previousState;

    @Override
    public Optional<BroadcastState> getPreviousState() {
        return Optional.fromNullable(previousState);
    }
}
