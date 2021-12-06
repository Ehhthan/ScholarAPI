package com.ehhthan.scholarapi.location;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

public class NamespaceKeyFactoryImpl implements NamespacedKeyFactory {
    @Override
    public NamespacedKey namespacedKey(String namespace, String key) {
        return new NamespacedKeyImpl(namespace, key);
    }
}
