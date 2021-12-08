package com.ehhthan.scholarapi.asset.file;

import com.ehhthan.scholarapi.location.NamespacedKey;

import java.io.File;

public class AssetFileImpl implements AssetFile {
    private final File file;
    private final Type type;
    private final NamespacedKey namespacedKey;

    AssetFileImpl(File file, Type type, NamespacedKey namespacedKey) {
        this.file = file;
        this.type = type;
        this.namespacedKey = namespacedKey;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public File asFile() {
        return file;
    }

    @Override
    public NamespacedKey asNamespacedKey() {
        return namespacedKey;
    }
}
