package org.amv.access.sdk.spi.bluetooth;

import com.google.common.base.Optional;

/**
 * An interface representing a broadcast state change.
 */
public interface BroadcastStateChangeEvent {
    BroadcastState getCurrentState();

    Optional<BroadcastState> getPreviousState();
}
