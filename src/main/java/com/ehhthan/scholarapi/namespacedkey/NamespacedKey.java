package com.ehhthan.scholarapi.namespacedkey;

public interface NamespacedKey {
    char SEPARATOR = ':';

    String namespace();

    String key();
}
