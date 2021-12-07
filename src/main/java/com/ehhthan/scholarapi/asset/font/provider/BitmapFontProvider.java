package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.TextureAssetFile;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;

public interface BitmapFontProvider extends FontProvider {
    TextureAssetFile file();

    int height();

    int ascent();

    String[] chars();

    @Override
    default String type() {
        return "bitmap";
    }

    FontCharacter character(char c);

    boolean hasCharacter(char c);
}
