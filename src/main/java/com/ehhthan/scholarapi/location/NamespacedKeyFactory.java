package com.ehhthan.scholarapi.location;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;

public interface NamespacedKeyFactory {
    NamespacedKey namespacedKey(@NotNull @Named("path") String path);

    class NamespacedKeyFactoryImpl implements NamespacedKeyFactory {
        @Override
        public NamespacedKey namespacedKey(@NotNull String path) {
            return new NamespacedKeyImpl(path);
        }
    }
}
