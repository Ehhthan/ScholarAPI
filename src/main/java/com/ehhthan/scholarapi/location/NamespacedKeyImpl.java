package com.ehhthan.scholarapi.location;

// TODO: 12/7/2021 check if namespace is required and if not what it should default to
public class NamespacedKeyImpl implements NamespacedKey {
    private final String namespace;
    private final String key;

    NamespacedKeyImpl(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
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
        if (!(o instanceof NamespacedKey key)) return false;

        return toString().equals(key.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
