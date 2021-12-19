package com.ehhthan.scholarapi.asset.texture;

import java.awt.image.BufferedImage;

public class TiledTextureAsset implements TextureAsset {
    private final int tileWidth, tileHeight;

    private final BufferedImage[][] textures;

    public TiledTextureAsset(int tileWidth, int tileHeight, BufferedImage[][] textures) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.textures = textures;
    }

    public int tileWidth() {
        return tileWidth;
    }

    public int tileHeight() {
        return tileHeight;
    }

    /**
     * textures should be arranged like
     * [[0][0]0,[0][1]1,2]
     * [3,4,5]
     * [6,7,8]
     */
    public BufferedImage[][] textures() {
        return textures;
    }
}
