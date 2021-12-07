package com.ehhthan.scholarapi.asset.file;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Named;

public interface AssetFileFactory {
    @Named("texture") TextureAssetFile texture(@Assisted String minecraftPath);

    @Named("model") ModelAssetFile model(@Assisted String minecraftPath);

    @Named("font") FontAssetFile font(@Assisted String minecraftPath);
}
