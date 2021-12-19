package com.ehhthan.scholarapi.asset;

import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.util.Locale;

public enum AssetLocation {
    BLOCKSTATES,
    FONT,
    LANG,
    MODELS,
    PARTICLES,
    SHADERS,
    TEXTS,
    TEXTURES;

    private final String name = name().toLowerCase(Locale.ROOT);

    public String path(String namespace) {
        return namespace + '/' + name + '/';
    }

    public String path(NamespacedKey namespacedKey) {
        return namespacedKey.namespace() + '/' + name + '/' + namespacedKey.key();
    }
}
