package com.ehhthan.scholarapi.asset.text;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class JsonTextAsset implements TextAsset {
    private final Component component;

    @Inject
    JsonTextAsset(@Assisted @NotNull String json) {
        this.component = GsonComponentSerializer.gson().deserialize(json);
    }

    @Override
    public Component asComponent() {
        return component;
    }
}
