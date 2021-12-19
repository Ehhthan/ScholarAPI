package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public interface FontAssetFactory {
    FontAsset provider(NamespacedKey namespacedKey, FontProvider... providers);

    FontAsset json(NamespacedKey namespacedKey, JsonObject json);

    FontAsset file(File file);

    class FontAssetFactoryImpl implements FontAssetFactory {
        //private static final Pattern EXTENSION_PATTERN = Pattern.compile("(?<extension>[.][^/.]+$)");

        private final FontProviderFactory providerFactory;
        private final NamespacedKeyFactory namespacedKeyFactory;
        private final Gson gson;

        @Inject
        public FontAssetFactoryImpl(FontProviderFactory factory, NamespacedKeyFactory namespacedKeyFactory, Gson gson) {
            this.providerFactory = factory;
            this.namespacedKeyFactory = namespacedKeyFactory;
            this.gson = gson;
        }

        @Override
        public FontAsset provider(NamespacedKey namespacedKey, FontProvider... providers) {
            return new FontAssetImpl(namespacedKey, providers);
        }

        @Override
        public FontAsset json(NamespacedKey namespacedKey, JsonObject json) {
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
            return new FontAssetImpl(namespacedKey, providers);
        }

        @Override
        public FontAsset file(File file) {
            BufferedReader reader;

            try {
                reader = Files.newBufferedReader(file.toPath());
            } catch (IOException e) {
                throw new IllegalArgumentException("File is not a valid font: " + file.toPath());
            }

            return json(namespacedKeyFactory.filePath(file), gson.fromJson(reader, JsonObject.class));
        }
    }
}
