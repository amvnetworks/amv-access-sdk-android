package org.amv.access.sdk.spi.bluetooth;

import com.google.common.base.Optional;

/**
 * An interface representing a connection state change.
 */
public interface ConnectionStateChangeEvent {
    ConnectionState getCurrentState();

    Optional<ConnectionState> getPreviousState();
}
