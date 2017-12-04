package org.amv.access.sdk.hm.bluetooth;

import org.amv.access.sdk.spi.bluetooth.ConnectionStateChangeEvent;
import org.amv.access.sdk.spi.bluetooth.IncomingCommandEvent;

import io.reactivex.Observable;

public interface BluetoothConnection {
    Observable<ConnectionStateChangeEvent> observeConnectionState();

    Observable<IncomingCommandEvent> observeIncomingCommands();

    Observable<Boolean> sendCommand(byte[] command);

}
