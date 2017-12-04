package org.amv.access.sdk.spi.communication;

/**
 * An interface representing a command sent to a connected device.
 */
public interface Command {
    Type getType();

    byte[] getBytes();

    interface Type {
        String getId();
    }
}
