package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.ehhthan.scholarapi.asset.texture.TextureAssetFactory;
import com.ehhthan.scholarapi.asset.texture.TiledTextureAsset;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKeyFactory;
import com.google.common.base.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;

public interface FontProviderFactory {
    BitmapFontProvider bitmap(JsonObject json);

    class FontProviderFactoryImpl implements FontProviderFactory {

        private final TextureAssetFactory textureFactory;
        private final NamespacedKeyFactory namespacedKeyFactory;
        private final FontCharacterFactory fontCharacterFactory;

        @Inject
        public FontProviderFactoryImpl(TextureAssetFactory textureFactory, NamespacedKeyFactory namespacedKeyFactory, FontCharacterFactory fontCharacterFactory) {
            this.textureFactory = textureFactory;
            this.namespacedKeyFactory = namespacedKeyFactory;
            this.fontCharacterFactory = fontCharacterFactory;
        }

        @Override
        public BitmapFontProvider bitmap(JsonObject json) {
            int height = json.has("height") ? json.getAsJsonPrimitive("height").getAsInt() : 8;
            int ascent = json.getAsJsonPrimitive("ascent").getAsInt();

            JsonArray jsonChars = json.getAsJsonArray("chars");

            String[] chars = new String[jsonChars.size()];
            for (int i = 0; i < jsonChars.size(); i++) {
                chars[i] = jsonChars.get(i).getAsString();
            }

            Preconditions.checkArgument(chars.length > 0 && chars[0].codePoints().toArray().length > 0, "Provider does not define any characters: %s",
                json.getAsJsonPrimitive("file").getAsString());

            Preconditions.checkArgument(ascent <= height, "Ascent cannot be greater than height: %s > %s", ascent, height);

            NamespacedKey namespacedKey = namespacedKeyFactory.minecraftPath(json.getAsJsonPrimitive("file").getAsString());

            TiledTextureAsset texture = textureFactory.tiled(chars.length, chars[0].codePoints().toArray().length, namespacedKey);

            return new BitmapFontProviderImpl(namespacedKey, height, ascent, chars, fontCharacterFactory.tiled(chars, texture));
        }
    }
}
