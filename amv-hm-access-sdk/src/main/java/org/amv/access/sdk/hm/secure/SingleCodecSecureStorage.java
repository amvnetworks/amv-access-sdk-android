package org.amv.access.sdk.hm.secure;

import com.google.common.base.Optional;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class SingleCodecSecureStorage implements Storage, SecureStorage {

    private final Storage storage;
    private final Codec codec;

    public SingleCodecSecureStorage(Storage storage, Codec codec) {
        this.storage = checkNotNull(storage);
        this.codec = checkNotNull(codec);
    }

    @Override
    public Observable<Optional<String>> findString(String key) {
        return Observable.just(1)
                .flatMap(foo -> storage.findString(key))
                .map(val -> val.transform(codec::decryptData));
    }

    @Override
    public Observable<Boolean> storeString(String key, String value) {
        return Observable.just(1)
                .map(foo -> codec.encryptData(value))
                .flatMap(encryptedValue -> storage.storeString(key, encryptedValue));
    }
}