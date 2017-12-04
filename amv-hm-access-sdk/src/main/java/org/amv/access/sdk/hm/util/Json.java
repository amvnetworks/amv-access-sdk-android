package org.amv.access.sdk.hm.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public final class Json {
    private static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    public static String toJson(Object val) {
        return GSON.toJson(val);
    }

    public static <T> T fromJson(String val, Type type) {
        return GSON.fromJson(val, type);
    }

    private Json() {
        throw new UnsupportedOperationException();
    }
}
