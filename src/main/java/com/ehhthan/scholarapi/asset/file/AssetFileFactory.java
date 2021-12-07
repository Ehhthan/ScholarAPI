package com.ehhthan.scholarapi.asset.file;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Named;

public interface AssetFileFactory {
    @Named("texture") TextureAssetFile textureFile(@Assisted String minecraftPath);

    @Named("model") ModelAssetFile modelFile(@Assisted String minecraftPath);
}
