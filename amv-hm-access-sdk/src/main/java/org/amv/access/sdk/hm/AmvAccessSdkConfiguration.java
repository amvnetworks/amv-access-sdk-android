package org.amv.access.sdk.hm;

import android.content.Context;
import android.content.SharedPreferences;

import com.highmobility.hmkit.Manager;

import org.amv.access.sdk.hm.certificate.AmvHmRemote;
import org.amv.access.sdk.hm.certificate.HmCertificateManager;
import org.amv.access.sdk.hm.certificate.HmLocalStorage;
import org.amv.access.sdk.hm.certificate.LocalStorage;
import org.amv.access.sdk.hm.certificate.Remote;
import org.amv.access.sdk.hm.communication.HmCommandFactory;
import org.amv.access.sdk.hm.secure.AndroidCodec;
import org.amv.access.sdk.hm.secure.Codec;
import org.amv.access.sdk.hm.secure.PlaintextCodec;
import org.amv.access.sdk.hm.secure.SecureStorage;
import org.amv.access.sdk.hm.secure.SharedPreferencesStorage;
import org.amv.access.sdk.hm.secure.SingleCodecSecureStorage;
import org.amv.access.sdk.hm.secure.Storage;

import static android.content.Context.MODE_PRIVATE;
import static com.google.common.base.Preconditions.checkNotNull;

class AmvAccessSdkConfiguration {
    private final Context context;
    private final AccessApiContext accessApiContext;

    AmvAccessSdkConfiguration(Context context, AccessApiContext accessApiContext) {
        this.context = checkNotNull(context);
        this.accessApiContext = checkNotNull(accessApiContext);
    }

    AmvAccessSdk amvAccessSdk() {
        return new AmvAccessSdk(context,
                Manager.getInstance(),
                certificateManager(),
                commandFactory());
    }

    private HmCommandFactory commandFactory() {
        return new HmCommandFactory();
    }

    private HmCertificateManager certificateManager() {
        Manager manager = Manager.getInstance();
        return new HmCertificateManager(manager, localStorage(), remote());
    }

    private Remote remote() {
        return new AmvHmRemote(accessApiContext);
    }

    private LocalStorage localStorage() {
        return new HmLocalStorage(secureStorage(), plaintextStorage());
    }

    private Storage plaintextStorage() {
        SharedPreferencesStorage sharedPreferencesStorage = sharedPreferencesStorage();
        return new SingleCodecSecureStorage(sharedPreferencesStorage, new PlaintextCodec());
    }

    private SecureStorage secureStorage() {
        SharedPreferencesStorage sharedPreferencesStorage = sharedPreferencesStorage();
        return new SingleCodecSecureStorage(sharedPreferencesStorage, androidCodec());
    }

    private SharedPreferencesStorage sharedPreferencesStorage() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("HM_SHARED_PREFS_ALIAS", MODE_PRIVATE);
        return new SharedPreferencesStorage(sharedPreferences);
    }

    private Codec androidCodec() {
        return new AndroidCodec(AndroidCodec.Options.builder()
                .keyAlias("AMV_HM_SECRET_CARRIER_KEY")
                .encryptedKeyName("AMV_HM_SECRET_CARRIER_ENC_KEY")
                .initVector(new byte[]{55, 54, 53, 52, 51, 50, 49, 48, 47, 46, 45, 44})
                .aesMode("AES/GCM/NoPadding")
                .aesKeyLength(128)
                .build());
    }
}
