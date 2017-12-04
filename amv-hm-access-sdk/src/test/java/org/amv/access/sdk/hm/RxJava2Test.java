package org.amv.access.sdk.hm;

import org.junit.Test;

import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;

public class RxJava2Test {
    @Test
    public void verifyEmptyObservableEmitsEmptyList() throws Exception {
        List<Object> list = Observable.empty()
                .toList()
                .blockingGet();

        assertEquals(list.isEmpty(), true);
    }
}