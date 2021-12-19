package com.ehhthan.scholarapi.asset.texture;

import java.awt.image.BufferedImage;

public class LoneTextureAsset implements TextureAsset {
    private final BufferedImage texture;

    public LoneTextureAsset(BufferedImage texture) {
        this.texture = texture;
    }

    public BufferedImage texture() {
        return texture;
    }
}
