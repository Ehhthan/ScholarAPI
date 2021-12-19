package com.ehhthan.scholarapi.location;

public interface NamespacedKey {
    char SEPARATOR = ':';

    String namespace();

    String key();
}
