package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.util.Map;

public interface FontAsset {
    NamespacedKey namespacedKey();

    FontProvider[] providers();

    Map<Integer, FontCharacter> fontCharacters();

    default boolean isFontCharacter(int codepoint) {
        return fontCharacters().containsKey(codepoint);
    }

    default FontCharacter fontCharacter(int codepoint) {
        return fontCharacters().get(codepoint);
    }
}
