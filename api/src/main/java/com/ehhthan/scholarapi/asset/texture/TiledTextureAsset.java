package com.ehhthan.scholarapi.asset.texture;

import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.awt.image.BufferedImage;

public class TiledTextureAsset implements TextureAsset {
    private final BufferedImage[][] textures;

    public TiledTextureAsset(BufferedImage[][] textures) {
        this.textures = textures;
    }

    /**
     * [rowIndex][columnIndex]
     */
    public BufferedImage[][] textures() {
        return textures;
    }

    @Override
    public NamespacedKey namespacedKey() {
        return null;
    }
}
