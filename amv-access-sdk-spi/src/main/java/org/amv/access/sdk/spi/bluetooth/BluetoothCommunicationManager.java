package org.amv.access.sdk.spi.bluetooth;

import org.amv.access.sdk.spi.communication.CommunicationManager;
import org.amv.access.sdk.spi.error.AccessSdkException;

import io.reactivex.Observable;

/**
 * An interface extending {@link CommunicationManager} with methods for handling bluetooth communications.
 * <p>
 * In addition to the methods provided by {@link CommunicationManager} there
 * are mechanisms to hook into the bluetooth connection handling.
 * This way one can react to certain events, e.g. broadcasting failed,
 * device communication channel has been closed, incoming errors, etc.
 * <p>
 * This is useful if you want to inform the user about the current state of the bluetooth communication.
 */
public interface BluetoothCommunicationManager extends CommunicationManager {
    /**
     * Observe every change in the broadcast state.
     *
     * @return an observable emitting broadcast state changes
     */
    Observable<BroadcastStateChangeEvent> observeBroadcastState();

    /**
     * Observe every change in the connection state.
     *
     * @return an observable emitting connection state changes
     */
    Observable<ConnectionStateChangeEvent> observeConnectionState();

    /**
     * Observe every incoming command from an established connection.
     * <p>
     * This method is useful when extending the sdk to emit
     * more fine grained events, e.g. transforming the command into a vehicle state update.
     *
     * @return an observable emitting commands coming from the connected device
     */
    Observable<IncomingCommandEvent> observeIncomingCommands();

    /**
     * Observe every incoming failure message.
     * <p>
     * This method represents a way to communicate failures.
     * The returned object is wrapped in an {@link AccessSdkException}.
     *
     * @return an observable emitting error messages coming from the connected device
     */
    Observable<AccessSdkException> observeIncomingFailureMessages();
}
