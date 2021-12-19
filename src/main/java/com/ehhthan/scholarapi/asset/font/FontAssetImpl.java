package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterImpl;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.location.NamespacedKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FontAssetImpl implements FontAsset {
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