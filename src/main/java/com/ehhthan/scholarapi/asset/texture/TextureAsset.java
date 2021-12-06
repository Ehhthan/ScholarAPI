package com.ehhthan.scholarapi.asset.texture;

import com.ehhthan.scholarapi.asset.file.AssetFile;

import java.awt.image.BufferedImage;

public interface TextureAsset {
    AssetFile file();

    BufferedImage texture();
}
