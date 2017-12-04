package org.amv.access.sdk.spi.bluetooth;

/**
 * An interface representing a connection state.
 * A connection can either be
 * <ul>
 * <li>disconnected (no communication possible)</li>
 * <li>connected (device has been found and connection is established)</li>
 * <li>authenticated (communication is secure and commands can be sent)</li>
 * </ul>
 */
public interface ConnectionState {
    boolean isAuthenticated();

    boolean isConnected();

    default boolean isDisconnected() {
        return !isConnected() && !isAuthenticated();
    }

}
