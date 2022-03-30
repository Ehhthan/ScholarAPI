package com.ehhthan.scholarapi.asset.text;

import com.ehhthan.scholarapi.asset.text.TextAsset.TextAssetImpl;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

public interface TextAssetFactory {
    TextAssetImpl json(@NotNull JsonObject json);

    TextAssetImpl string(@NotNull String text);

    class TextAssetFactoryImpl implements TextAssetFactory {
        @Override
        public TextAssetImpl json(@NotNull JsonObject json) {
            return new TextAssetImpl(json.toString());
        }

        @Override
        public TextAssetImpl string(@NotNull String text) {
            return new TextAssetImpl(text);
        }
    }
}
