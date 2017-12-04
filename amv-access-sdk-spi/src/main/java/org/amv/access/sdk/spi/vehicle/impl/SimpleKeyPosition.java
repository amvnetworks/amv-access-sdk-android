package org.amv.access.sdk.spi.vehicle.impl;

import org.amv.access.sdk.spi.vehicle.VehicleState;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SimpleKeyPosition implements VehicleState.KeyPosition {
    private boolean known;
}
