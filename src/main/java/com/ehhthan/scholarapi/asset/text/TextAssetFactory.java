package com.ehhthan.scholarapi.asset.text;

import com.google.inject.name.Named;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface TextAssetFactory {
    @Named("json") JsonTextAsset jsonAsset(@NotNull String json);
    @Named("string") StringTextAsset stringAsset(@NotNull String text);
}
