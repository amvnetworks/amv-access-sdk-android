package org.amv.access.sdk.hm.communication;

import com.highmobility.autoapi.Command.DoorLocks;
import com.highmobility.autoapi.Command.VehicleStatus;

import org.amv.access.sdk.spi.communication.Command;
import org.amv.access.sdk.spi.communication.CommandFactory;
import org.amv.access.sdk.spi.communication.impl.SimpleCommand;

public class HmCommandFactory implements CommandFactory {
    @Override
    public Command lockDoors() {
        return new SimpleCommand("DOOR_LOCK", DoorLocks.lockDoors(true));
    }

    @Override
    public Command unlockDoors() {
        return new SimpleCommand("DOOR_UNLOCK", DoorLocks.lockDoors(false));
    }

    @Override
    public Command sendVehicleStatus() {
        return new SimpleCommand("SEND_VEHICLE_STATE", VehicleStatus.getVehicleStatus());
    }

}
