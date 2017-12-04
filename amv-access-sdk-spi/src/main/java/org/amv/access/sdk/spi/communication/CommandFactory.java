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
}
