package com.ehhthan.scholarapi.asset;

import com.ehhthan.scholarapi.location.NamespacedKey;

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



    public String path(NamespacedKey namespacedKey) {
        return namespacedKey.namespace() + '/' + name().toLowerCase(Locale.ROOT) + '/' + namespacedKey.key();
    }
}
