package com.ehhthan.scholarapi.asset.text;

import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;

public interface TextAssetFactory {
    @Named("json") JsonTextAsset jsonAsset(@NotNull String json);
    @Named("string") StringTextAsset stringAsset(@NotNull String text);

    class TextAssetFactoryImpl implements TextAssetFactory {
        @Override
        public JsonTextAsset jsonAsset(@NotNull String json) {
            return new JsonTextAsset(json);
        }

        @Override
        public StringTextAsset stringAsset(@NotNull String text) {
            return new StringTextAsset(text);
        }
    }
}
