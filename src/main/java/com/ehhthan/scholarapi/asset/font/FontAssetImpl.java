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

    @Inject
    FontAssetImpl(FontProviderFactory providerFactory, Gson gson, @Assisted AssetFile file) throws IOException {
        this.namespacedKey = file.asNamespacedKey();

        BufferedReader reader = Files.newBufferedReader(file.asFile().toPath());
        JsonObject json = gson.fromJson(reader, JsonObject.class);

        JsonArray jsonProviders = json.getAsJsonArray("providers");
        this.providers = new FontProvider[jsonProviders.size()];
        for (int i = 0; i < jsonProviders.size(); i++) {
            JsonObject providerObject = jsonProviders.get(i).getAsJsonObject();

            switch (FontProvider.Type.fromPath(providerObject.get("type").getAsString())) {
                case BITMAP:
                    FontProvider provider = providerFactory.bitmap(providerObject);
                    this.providers[i] = provider;
                    fontCharacters.putAll(provider.fontCharacters());
                case TTF:
                    //throw new UnsupportedOperationException("TTF type not supported yet.");
                case LEGACY_UNICODE:
                    //throw new UnsupportedOperationException("Legacy_unicode type not supported yet.");
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