package org.amv.access.sdk.spi.communication.impl;

import org.amv.access.sdk.spi.communication.Command;

import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;

public class SimpleCommand implements Command {
    private final Type type;
    private final byte[] command;

    public SimpleCommand(String type, byte[] command) {
        this(new SimpleType(type), command);
    }

    private SimpleCommand(Type type, byte[] command) {
        this.type = checkNotNull(type);
        this.command = Arrays.copyOf(command, command.length);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public byte[] getBytes() {
        return Arrays.copyOf(command, command.length);
    }
}
