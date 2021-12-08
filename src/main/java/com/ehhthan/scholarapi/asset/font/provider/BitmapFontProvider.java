package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;

import java.util.Map;

public interface BitmapFontProvider extends FontProvider {
    AssetFile file();

    int height();

    int ascent();

    String[] chars();

    @Override
    default Type type() {
        return Type.BITMAP;
    }

    Map<Integer, FontCharacter> charMap();
}
