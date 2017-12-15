package org.amv.access.sdk.spi.communication;

/**
 * An interface for creating commands which can
 * be used to interact with a connected device.
 */
public interface CommandFactory {
    /**
     * A command for locking the doors on a vehicle.
     *
     * @return a command to lock doors
     */
    Command lockDoors();

    /**
     * A command for unlocking the doors on a vehicle.
     *
     * @return a command to unlock doors
     */
    Command unlockDoors();

    /**
     * A command representing the request to send the current vehicle state.
     *
     * @return a command to which will be responded with the vehicle state
     */
    Command sendVehicleStatus();

    /**
     * A command for closing the Bluetooth connection on module side.
     * We need this, cause devices >= Android 6 don't close the physical connection,
     * if broadcasting has been stopped.
     *
     * @return a command for closing Bluetooth connection.
     */
    Command disconnect();
}
