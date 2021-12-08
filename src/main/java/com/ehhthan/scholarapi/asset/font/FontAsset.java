package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.location.NamespacedKey;

import java.util.Map;

public interface FontAsset {
    NamespacedKey namespacedKey();

    FontProvider[] providers();

    Map<Integer, FontCharacter> fontCharacters();

    default boolean hasCharacter(char... chars) {
        if (chars.length == 1)
            return hasCharacter(chars[0]);
        else if (chars.length >= 2)
            return hasCharacter(Character.toCodePoint(chars[0], chars[1]));
        else
            return false;
    }

    default boolean hasCharacter(int codepoint) {
        return fontCharacters().containsKey(codepoint);
    }

    default FontCharacter getCharacter(char... chars) {
        if (chars.length == 1)
            return getCharacter(chars[0]);
        else if (chars.length >= 2)
            return getCharacter(Character.toCodePoint(chars[0], chars[1]));
        else
            return null;
    }

    default FontCharacter getCharacter(int codepoint) {
        return fontCharacters().get(codepoint);
    }
}
