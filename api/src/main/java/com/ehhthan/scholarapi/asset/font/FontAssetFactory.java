package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKeyFactory;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface FontAssetFactory {
    FontAsset provider(NamespacedKey namespacedKey, FontProvider... providers);

    FontAsset file(File file);

    class FontAssetFactoryImpl implements FontAssetFactory {
        //private static final Pattern EXTENSION_PATTERN = Pattern.compile("(?<extension>[.][^/.]+$)");

        private final Logger logger;
        private final FontProviderFactory providerFactory;
        private final NamespacedKeyFactory namespacedKeyFactory;
        private final Gson gson;

        @Inject
        public FontAssetFactoryImpl(Logger logger, FontProviderFactory factory, NamespacedKeyFactory namespacedKeyFactory, Gson gson) {
            this.logger = logger;
            this.providerFactory = factory;
            this.namespacedKeyFactory = namespacedKeyFactory;
            this.gson = gson;
        }

        @Override
        public FontAsset provider(NamespacedKey namespacedKey, FontProvider... providers) {
            return new FontAsset.FontAssetImpl(namespacedKey, providers);
        }

        @Override
        public FontAsset file(File file) {
            BufferedReader reader;
            try {
                reader = Files.newBufferedReader(file.toPath());
            } catch (IOException e) {
                throw new IllegalArgumentException("File is not a valid font: " + file.toPath());
            }

            JsonObject json = gson.fromJson(reader, JsonObject.class);

            Preconditions.checkArgument(json.has("providers"), "No providers defined.");
            JsonArray fileProviders = json.getAsJsonArray("providers");

            FontProvider[] fontProviders = new FontProvider[fileProviders.size()];
            for (int i = 0; i < fileProviders.size(); i++) {
                JsonObject providerObject = fileProviders.get(i).getAsJsonObject();

                switch (FontProvider.Type.fromPath(providerObject.get("type").getAsString())) {
                    case BITMAP:
                        FontProvider provider = providerFactory.bitmap(providerObject);
                        fontProviders[i] = provider;
                    case TTF, LEGACY_UNICODE:
                        logger.log(Level.WARNING, "Font provider type is not yet supported.");
                }
            }
            return new FontAsset.FontAssetImpl(namespacedKeyFactory.filePath(file.toPath()), fontProviders);
        }
    }
}
