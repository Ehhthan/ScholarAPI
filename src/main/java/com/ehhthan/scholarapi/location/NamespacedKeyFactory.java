package com.ehhthan.scholarapi.location;

import com.google.inject.assistedinject.Assisted;
import org.jetbrains.annotations.NotNull;

public interface NamespacedKeyFactory {
    NamespacedKey namespacedKey(@NotNull @Assisted("namespace") String namespace, @NotNull @Assisted("key") String key);
}
