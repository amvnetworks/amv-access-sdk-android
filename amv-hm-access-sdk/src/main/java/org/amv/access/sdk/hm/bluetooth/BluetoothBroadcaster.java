package org.amv.access.sdk.hm.bluetooth;

import org.amv.access.sdk.spi.bluetooth.BroadcastStateChangeEvent;
import org.amv.access.sdk.spi.certificate.AccessCertificatePair;

import io.reactivex.Observable;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

public interface BluetoothBroadcaster {
    Observable<Boolean> startBroadcasting(AccessCertificatePair accessCertificatePair);

    Observable<BroadcastStateChangeEvent> observeBroadcastStateChanges();

    Observable<BluetoothConnectionEvent> observeConnections();

    Observable<Boolean> terminate();

    @Value
    @Builder
    class BluetoothConnectionEvent {
        static BluetoothConnectionEvent connected(BluetoothConnection link) {
            return BluetoothConnectionEvent.builder()
                    .connection(link)
                    .connected(true)
                    .build();
        }

        static BluetoothConnectionEvent disconnected(BluetoothConnection link) {
            return BluetoothConnectionEvent.builder()
                    .connection(link)
                    .connected(false)
                    .build();
        }

        @NonNull
        private final BluetoothConnection connection;
        private final boolean connected;

        boolean isConnected() {
            return connected;
        }

        boolean isDisconnected() {
            return !isConnected();
        }
    }
}
