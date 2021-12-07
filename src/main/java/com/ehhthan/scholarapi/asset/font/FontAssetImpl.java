package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.file.FontAssetFile;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

public class FontAssetImpl implements FontAsset {
    private final NamespacedKey namespacedKey;
    private final FontProvider[] providers;

    @Inject
    FontAssetImpl(FontProviderFactory providerFactory, @Assisted FontAssetFile file) throws IOException {
        this.namespacedKey = file.namespacedKey();

        Gson gson = new Gson();
        BufferedReader reader = Files.newBufferedReader(file.asFile().toPath());

        JsonObject json = gson.fromJson(reader, JsonObject.class);

        JsonArray jsonProviders = json.getAsJsonArray("providers");
        this.providers = new FontProvider[jsonProviders.size()];
        for (int i = 0; i < jsonProviders.size(); i++) {
            JsonObject providerObject = jsonProviders.get(i).getAsJsonObject();

            switch (providerObject.get("type").getAsString()) {
                case "bitmap":
                    this.providers[i] = providerFactory.bitmap(providerObject);
                case "ttf":
                    //throw new UnsupportedOperationException("TTF type not supported yet.");
                case "legacy_unicode":
                    //throw new UnsupportedOperationException("Legacy_unicode type not supported yet.");
                default:
                    //throw new IllegalArgumentException("Invalid font type.");
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
}
