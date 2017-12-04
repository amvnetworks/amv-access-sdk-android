package org.amv.access.sdk.spi.bluetooth;

/**
 * An interface representing a broadcast state.
 * <p>
 * A broadcast state can either be
 * <ul>
 * <li>idle (no broadcasting is done)</li>
 * <li>broadcasting (actively looking for devices)</li>
 * </ul>
 * <p>
 * Additionally {@link #isBluetoothEnabled()} indicates whether bluetooth
 * is currently available. Unless bluetooth is available {@link #isBroadcasting()}
 * will never return true and the broadcast remains in state 'idle'.
 */
public interface BroadcastState {
    boolean isIdle();

    boolean isBroadcasting();

    boolean isBluetoothEnabled();
}
