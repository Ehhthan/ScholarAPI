package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.google.gson.JsonObject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public interface FontProviderFactory {
    @Named("bitmap") BitmapFontProvider bitmap(@Assisted JsonObject json);

    class FontProviderFactoryImpl implements FontProviderFactory {

        public FontProviderFactoryImpl() {

        }

        @Override
        public BitmapFontProvider bitmap(JsonObject json) {
            return null;
        }
    }
}
