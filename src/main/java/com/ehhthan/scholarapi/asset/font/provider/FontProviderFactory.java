package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.TextureAssetFile;
import com.ehhthan.scholarapi.asset.text.TextAsset;
import com.google.gson.JsonObject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;

public interface FontProviderFactory {
    @Named("bitmap") BitmapFontProvider bitmap(@Assisted JsonObject json);
}
