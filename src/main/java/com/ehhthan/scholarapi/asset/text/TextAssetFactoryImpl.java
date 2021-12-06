package com.ehhthan.scholarapi.asset.text;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class TextAssetFactoryImpl implements TextAssetFactory{
    @Override
    public TextAsset jsonAsset(@NotNull String json) {
        return new JsonTextAsset(json);
    }

    @Override
    public TextAsset stringAsset(@NotNull String text) {
        return new StringTextAsset(text);
    }
}
