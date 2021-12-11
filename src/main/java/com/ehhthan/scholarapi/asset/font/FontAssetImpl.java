package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class FontAssetImpl implements FontAsset {
    private final NamespacedKey namespacedKey;
    private final FontProvider[] providers;

    private final Map<Integer, FontCharacter> fontCharacters = new HashMap<>();

    FontAssetImpl(NamespacedKey namespacedKey, FontProvider[] providers) {
        this.namespacedKey = namespacedKey;
        this.providers = providers;

        for (FontProvider provider : providers)
            fontCharacters.putAll(provider.fontCharacters());
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