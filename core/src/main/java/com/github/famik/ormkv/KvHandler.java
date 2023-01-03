package com.github.famik.ormkv;

import androidx.annotation.Nullable;

import java.util.ServiceLoader;
import java.util.Set;

/**
 * KvHandler interface
 * @see KvClass
 */
public interface KvHandler {
    void set(String key, boolean value);
    boolean get(String key, boolean defaultValue);

    void set(String key, int value);
    int get(String key, int defaultValue);

    void set(String key, long value);
    long get(String key, long defaultValue);

    void set(String key, float value);
    float get(String key, float defaultValue);

    void set(String key, @Nullable String value);
    @Nullable String get(String key, @Nullable String defaultValue);

    void set(String key, @Nullable byte[] value);
    @Nullable byte[] get(String key, @Nullable byte[] defaultValue);

    void set(String key, @Nullable Set<String> value);
    @Nullable Set<String> get(String key, @Nullable Set<String>defaultValue);

    void remove(String key);
    void clear();

    class Default {
        private static final KvHandler INSTANCE = ServiceLoader.load(KvHandler.class).iterator().next();
    }

    static KvHandler getDefault() {
        return Default.INSTANCE;
    }
}
