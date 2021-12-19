package com.ehhthan.scholarapi.asset.font.provider;


import com.ehhthan.scholarapi.asset.texture.TextureAsset;
import com.ehhthan.scholarapi.location.NamespacedKey;

public interface BitmapFontProvider extends FontProvider {
    NamespacedKey file();

    int height();

    int ascent();

    String[] chars();
}
