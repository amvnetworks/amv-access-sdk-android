package org.amv.access.sdk.spi.vehicle;


import com.google.common.base.Optional;

/**
 * An interface representing the vehicle state.
 */
public interface VehicleState {
    Optional<DoorLocksState> getDoorLockState();

    Optional<DoorsPositionState> getDoorPositionState();

    Optional<ChargingPlugState> getChargingPlugState();

    Optional<KeyPosition> getKeyPosition();

    Optional<Mileage> getMileage();

    interface Mileage {
        int getValue();
    }

    interface DoorLocksState {
        boolean isLocked();

        default boolean isUnlocked() {
            return !isLocked();
        }
    }

    interface DoorsPositionState {
        boolean isOpen();

        default boolean isClosed() {
            return !isOpen();
        }
    }

    interface ChargingPlugState {
        boolean isPlugged();

        default boolean isUnplugged() {
            return !isPlugged();
        }
    }

    interface KeyPosition {
        boolean isKnown();
    }
}
