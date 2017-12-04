package org.amv.access.sdk.spi.bluetooth.impl;

import org.amv.access.sdk.spi.bluetooth.IncomingCommandEvent;

import java.util.Arrays;

import lombok.Builder;

public class SimpleIncomingCommandEvent implements IncomingCommandEvent {
    private final byte[] command;

    @Builder
    SimpleIncomingCommandEvent(byte[] command) {
        this.command = Arrays.copyOf(command, command.length);
    }

    @Override
    public byte[] getCommand() {
        return Arrays.copyOf(command, command.length);
    }
}
