package com.ehhthan.scholarapi.asset.text;

import com.google.inject.name.Named;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface TextAssetFactory {
    @Named("json") TextAsset jsonAsset(@NotNull String json);
    @Named("string") TextAsset stringAsset(@NotNull String text);
}
