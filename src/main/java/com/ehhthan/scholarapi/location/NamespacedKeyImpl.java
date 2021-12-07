package com.ehhthan.scholarapi.location;

import org.jetbrains.annotations.NotNull;

import static com.ehhthan.scholarapi.location.NamespacedKeyValidator.*;

public final class NamespacedKeyImpl implements NamespacedKey {
    private final String namespace;
    private final String key;

    NamespacedKeyImpl(@NotNull String namespace, @NotNull String key) {
        validateNamespace(namespace);
        validateKey(key);

        this.namespace = namespace;
        this.key = key;

        validateNamespacedKey(this.toString());
    }

    @Override
    public String namespace() {
        return namespace;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String toString() {
        return String.join(":", namespace, key);
    }
}
