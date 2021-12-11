package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;

public interface FontAssetFactory {
    FontAsset file(AssetFile file);

    FontAsset provider(NamespacedKey namespacedKey, FontProvider... providers);

    class FontAssetFactoryImpl implements FontAssetFactory {
        private final FontProviderFactory providerFactory;
        private final Gson gson;

        @Inject
        public FontAssetFactoryImpl(FontProviderFactory factory, Gson gson) {
            this.providerFactory = factory;
            this.gson = gson;
        }

        @Override
        public FontAsset file(AssetFile file) {
            BufferedReader reader;
            try {
                reader = Files.newBufferedReader(file.asFile().toPath());
            } catch (IOException e) {
                throw new IllegalArgumentException("File is not a valid font: " + file.asFile().toPath());
            }
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            JsonArray jsonProviders = json.getAsJsonArray("providers");
            FontProvider[] providers = new FontProvider[jsonProviders.size()];
            for (int i = 0; i < jsonProviders.size(); i++) {
                JsonObject providerObject = jsonProviders.get(i).getAsJsonObject();

                switch (FontProvider.Type.fromPath(providerObject.get("type").getAsString())) {
                    case BITMAP:
                        FontProvider provider = providerFactory.bitmap(providerObject);
                        providers[i] = provider;
                    case TTF:
                        //throw new UnsupportedOperationException("TTF type not supported yet.");
                    case LEGACY_UNICODE:
                        //throw new UnsupportedOperationException("Legacy_unicode type not supported yet.");
                }
            }
            return new FontAssetImpl(file.asNamespacedKey(), providers);
        }

        @Override
        public FontAsset provider(NamespacedKey namespacedKey, FontProvider... providers) {
            return new FontAssetImpl(namespacedKey, providers);
        }
    }
}
