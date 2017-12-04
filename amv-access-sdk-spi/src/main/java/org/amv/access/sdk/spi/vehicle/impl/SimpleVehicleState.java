package org.amv.access.sdk.spi.vehicle.impl;

import com.google.common.base.Optional;

import org.amv.access.sdk.spi.vehicle.VehicleState;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SimpleVehicleState implements VehicleState {
    private DoorLocksState doorLocksState;
    private DoorsPositionState doorsPositionState;
    private ChargingPlugState chargingPlugState;
    private KeyPosition keyPosition;
    private Mileage mileage;

    @Override
    public Optional<DoorLocksState> getDoorLockState() {
        return Optional.fromNullable(doorLocksState);
    }

    @Override
    public Optional<DoorsPositionState> getDoorPositionState() {
        return Optional.fromNullable(doorsPositionState);
    }

    @Override
    public Optional<ChargingPlugState> getChargingPlugState() {
        return Optional.fromNullable(chargingPlugState);
    }

    @Override
    public Optional<KeyPosition> getKeyPosition() {
        return Optional.fromNullable(keyPosition);
    }

    @Override
    public Optional<Mileage> getMileage() {
        return Optional.fromNullable(mileage);
    }
}
