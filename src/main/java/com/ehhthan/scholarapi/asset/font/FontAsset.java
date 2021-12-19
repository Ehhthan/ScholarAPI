package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterImpl;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.location.NamespacedKey;

import java.io.FileFilter;
import java.util.Map;

public interface FontAsset {
    NamespacedKey namespacedKey();

    FontProvider[] providers();

    Map<Integer, FontCharacter> fontCharacters();

    default boolean hasCharacter(int codepoint) {
        return fontCharacters().containsKey(codepoint);
    }

    default FontCharacter getCharacter(int codepoint) {
        return fontCharacters().get(codepoint);
    }
}
