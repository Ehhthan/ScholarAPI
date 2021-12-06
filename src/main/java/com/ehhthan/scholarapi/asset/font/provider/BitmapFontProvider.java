package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.AssetFile;

public interface BitmapFontProvider extends FontProvider {

    AssetFile file();

    int height();

    int ascent();


    @Override
    default ProviderType type() {
        return ProviderType.BITMAP;
    }


}
