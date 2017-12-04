package org.amv.access.sdk.spi.bluetooth;

/**
 * Interface representing a command coming in from a connected device.
 */
public interface IncomingCommandEvent {
    byte[] getCommand();
}
