package org.amv.access.sdk.spi.communication;

import org.amv.access.sdk.spi.bluetooth.BluetoothCommunicationManager;
import org.amv.access.sdk.spi.certificate.AccessCertificatePair;
import org.amv.access.sdk.spi.vehicle.VehicleState;

import io.reactivex.Observable;

/**
 * An interface abstracting the mechanism to
 * start and stop communications with a connected device.
 */
public interface CommunicationManager {

    /**
     * This methods starts looking for devices eligible to handle the given
     * access certificate pair. This methods returns an observable that
     * will complete if looking for devices started successfully.
     * <p>
     * If the observable completes, it does NOT mean, that the device has been
     * successfully connected - but rather that the broadcasting started successfully.
     * If you want to be informed when a connection has been established, you have to
     * use a lower level interface like {@link BluetoothCommunicationManager}.
     * <p>
     * See {@link BluetoothCommunicationManager#observeBroadcastState()} or
     * {@link BluetoothCommunicationManager#observeConnectionState()} for more fine grained
     * access to the communication state.
     *
     * @param accessCertificatePair the access certificate used for establishing a communication channel
     * @return an observable that completes when looking for devices started successfully
     */
    Observable<Boolean> startConnecting(AccessCertificatePair accessCertificatePair);

    /**
     * An observable emitting incoming vehicle states.
     * This observable is only completes when the communication ends.
     * <p>
     * Depending on the concrete implementation, you may have
     * to send a command to request the vehicle state before
     * this observable emits any updates.
     *
     * @return an observable emitting vehicle states
     */
    Observable<VehicleState> observeVehicleState();

    /**
     * A mechanism to send commands to a connected device.
     * This method returns an observable that completes when the
     * command has been successfully sent.
     *
     * It does NOT indicate, that the command has been understood by the
     * connected device.
     *
     * @param command a command that should be sent to the device
     * @return an observable that completes when the command has been successfully sent
     */
    Observable<Boolean> sendCommand(Command command);

    /**
     * Terminate all communication
     *
     * Disconnect from a connected device and do cleanup operations.
     * This method ends all communication with the connected device and
     * closes all incoming streams.
     *
     * After this method has been invoked it may or may not be possible to call
     * {@link #startConnecting(AccessCertificatePair)} depending on the implementation.
     *
     * @return an observable that completes when disconnecting has been successful
     */
    Observable<Boolean> terminate();
}
