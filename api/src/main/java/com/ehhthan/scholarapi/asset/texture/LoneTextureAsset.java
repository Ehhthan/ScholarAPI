package com.ehhthan.scholarapi.asset.texture;

import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.awt.image.BufferedImage;

public class LoneTextureAsset implements TextureAsset {
    private final BufferedImage texture;

    public LoneTextureAsset(BufferedImage texture) {
        this.texture = texture;
    }

    public BufferedImage texture() {
        return texture;
    }

    @Override
    public NamespacedKey namespacedKey() {
        return null;
    }
}
