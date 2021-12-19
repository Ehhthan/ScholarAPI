package com.ehhthan.scholarapi.asset.font.provider;


import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

public interface BitmapFontProvider extends FontProvider {
    NamespacedKey file();

    int height();

    int ascent();

    String[] chars();
}
