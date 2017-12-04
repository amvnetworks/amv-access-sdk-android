package org.amv.access.sdk.spi.bluetooth.impl;

import com.google.common.base.Optional;

import org.amv.access.sdk.spi.bluetooth.ConnectionState;
import org.amv.access.sdk.spi.bluetooth.ConnectionStateChangeEvent;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class SimpleConnectionStateChangeEvent implements ConnectionStateChangeEvent {
    @NonNull
    private final ConnectionState currentState;
    private final ConnectionState previousState;

    @Override
    public Optional<ConnectionState> getPreviousState() {
        return Optional.fromNullable(previousState);
    }
}
