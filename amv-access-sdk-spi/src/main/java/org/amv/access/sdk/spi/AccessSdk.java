package org.amv.access.sdk.spi;

import org.amv.access.sdk.spi.bluetooth.BluetoothCommunicationManager;
import org.amv.access.sdk.spi.certificate.CertificateManager;
import org.amv.access.sdk.spi.communication.CommandFactory;

import io.reactivex.Observable;

public interface AccessSdk {
    /**
     * Initialization routine for the setting up the sdk.
     * <p>
     * After the returned observable completes, the sdk is ready to be used.
     *
     * @return an observable that completes on successful initialization
     */
    Observable<Boolean> initialize();

    /**
     * Provides a mechanism to handle and manage certificates.
     *
     * @return an instance of CertificateManager
     */
    CertificateManager certificateManager();

    /**
     * Provides a mechanism to handle the lifecycle of bluetooth communications
     * with a connected device.
     *
     * @return an instance of BluetoothCommunicationManager
     */
    BluetoothCommunicationManager createBluetoothCommunicationManager();

    /**
     * Provides a factory to create commands that can be used in communications
     * with connected devices.
     *
     * @return an instance of BluetoothCommunicationManager
     */
    CommandFactory commandFactory();
}
