package org.amv.access.sdk.hm.certificate;

import com.highmobility.utils.Bytes;

import org.amv.access.sdk.spi.certificate.AccessCertificate;

import java.util.Arrays;
import java.util.Calendar;

import static com.google.common.base.Preconditions.checkNotNull;

class HmAccessCertificate implements AccessCertificate {

    private final com.highmobility.crypto.AccessCertificate delegate;

    HmAccessCertificate(com.highmobility.crypto.AccessCertificate delegate) {
        this.delegate = checkNotNull(delegate);
    }

    @Override
    public byte[] toByteArray() {
        return Arrays.copyOf(delegate.getBytes(),
                delegate.getBytes().length);
    }

    @Override
    public String getProviderSerial() {
        return Bytes.hexFromBytes(delegate.getProviderSerial());
    }

    @Override
    public String getGainerSerial() {
        return Bytes.hexFromBytes(delegate.getGainerSerial());
    }

    @Override
    public Calendar getStartDate() {
        return delegate.getStartDate();
    }

    @Override
    public Calendar getEndDate() {
        return delegate.getEndDate();
    }

    @Override
    public boolean isExpired() {
        return delegate.isExpired();
    }

    @Override
    public boolean isNotValidYet() {
        return delegate.isNotValidYet();
    }

}
