package org.amv.access.sdk.hm;

import android.content.Context;
import android.util.Log;

import com.highmobility.hmkit.Broadcaster;
import com.highmobility.hmkit.Manager;

import org.amv.access.sdk.hm.bluetooth.BluetoothBroadcaster;
import org.amv.access.sdk.hm.bluetooth.HmBluetoothBroadcaster;
import org.amv.access.sdk.hm.bluetooth.HmBluetoothCommunicationManager;
import org.amv.access.sdk.hm.certificate.HmCertificateManager;
import org.amv.access.sdk.hm.communication.HmCommandFactory;
import org.amv.access.sdk.spi.AccessSdk;
import org.amv.access.sdk.spi.bluetooth.BluetoothCommunicationManager;
import org.amv.access.sdk.spi.certificate.CertificateManager;
import org.amv.access.sdk.spi.communication.CommandFactory;
import org.amv.access.sdk.spi.communication.CommunicationManagerFactory;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class AmvAccessSdk implements AccessSdk {

    public static AccessSdk create(Context context, AccessApiContext accessApiContext) {
        checkNotNull(context);
        checkNotNull(accessApiContext);

        AmvAccessSdkConfiguration config = new AmvAccessSdkConfiguration(context, accessApiContext);
        return config.amvAccessSdk();
    }

    private final Context context;
    private final Manager manager;
    private final HmCertificateManager certificateHandler;
    private final HmCommandFactory commandFactory;

    AmvAccessSdk(Context context, Manager manager,
                 HmCertificateManager certificateHandler,
                 HmCommandFactory commandFactory) {
        this.context = checkNotNull(context);
        this.manager = checkNotNull(manager);
        this.certificateHandler = checkNotNull(certificateHandler);
        this.commandFactory = checkNotNull(commandFactory);
    }

    @Override
    public Observable<Boolean> initialize() {
        return Observable.just(1)
                .doOnNext(foo -> {
                    Log.d("", "Initializing...");
                })
                .flatMap(foo -> certificateHandler.initialize(context))
                .map(foo -> true);
    }

    @Override
    public CertificateManager certificateManager() {
        return certificateHandler;
    }

    @Override
    public CommunicationManagerFactory<BluetoothCommunicationManager> bluetoothCommunicationManagerFactory() {
        return () -> new HmBluetoothCommunicationManager(createBluetoothBroadcaster(), commandFactory());
    }

    @Override
    public CommandFactory commandFactory() {
        return commandFactory;
    }

    private BluetoothBroadcaster createBluetoothBroadcaster() {
        Broadcaster b = manager.getBroadcaster();
        return new HmBluetoothBroadcaster(b);
    }
}
