package com.ehhthan.scholarapi.namespacedkey;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public interface NamespacedKey {
    char SEPARATOR = ':';

    String namespace();

    String key();

    final class NamespacedKeyImpl implements NamespacedKey {
        private final String namespace;
        private final String key;

        NamespacedKeyImpl(@NotNull String namespace, @NotNull String key) {
            this.namespace = namespace;
            this.key = key;

            Preconditions.checkArgument(toString().length() < 256, "NamespacedKey length must be less than 256 characters: %s", toString());
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
            return namespace + SEPARATOR + key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NamespacedKeyImpl namespacedKey = (NamespacedKeyImpl) o;

            if (!namespace.equals(namespacedKey.namespace)) return false;
            return key.equals(namespacedKey.key);
        }

        @Override
        public int hashCode() {
            int result = namespace.hashCode();
            result = 31 * result + key.hashCode();
            return result;
        }
    }
}
