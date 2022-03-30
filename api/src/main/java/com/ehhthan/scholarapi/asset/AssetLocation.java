package com.ehhthan.scholarapi.asset;

import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public enum AssetLocation {
    BLOCKSTATES,
    FONT,
    LANG,
    MODELS,
    PARTICLES,
    SHADERS,
    TEXTS,
    TEXTURES;

    private final String path;

    AssetLocation() {
        this.path = name().toLowerCase(Locale.ROOT);
    }

    AssetLocation(String path) {
        this.path = path;
    }

    public String path(String namespace) {
        return namespace + '/' + path + '/';
    }

    public String path(NamespacedKey namespacedKey) {
        return namespacedKey.namespace() + '/' + path + '/' + namespacedKey.key();
    }

    public String path() {
        return path;
    }
}
