package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.PackAsset;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public interface FontAsset extends PackAsset {
    FontProvider[] providers();

    Map<Integer, FontCharacter> fontCharacters();

    default boolean isFontCharacter(int codepoint) {
        return fontCharacters().containsKey(codepoint);
    }

    default FontCharacter fontCharacter(int codepoint) {
        return fontCharacters().get(codepoint);
    }

    final class FontAssetImpl implements FontAsset {
        private final NamespacedKey namespacedKey;
        private final FontProvider[] providers;

        private final transient Map<Integer, FontCharacter> fontCharacters = new HashMap<>();

        FontAssetImpl(NamespacedKey namespacedKey, FontProvider[] providers) {
            this.namespacedKey = namespacedKey;
            this.providers = providers;

            for (FontProvider provider : providers) {
                if (provider != null)
                    for (FontCharacter fontCharacter : provider.fontCharacters()) {
                        fontCharacters.put(fontCharacter.codepoint(), fontCharacter);
                    }
            }
        }

        @Override
        public NamespacedKey namespacedKey() {
            return namespacedKey;
        }

        @Override
        public FontProvider[] providers() {
            return providers;
        }

        @Override
        public Map<Integer, FontCharacter> fontCharacters() {
            return fontCharacters;
        }
    }
}
