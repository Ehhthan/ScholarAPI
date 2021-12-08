package com.ehhthan.scholarapi.asset.text;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.jetbrains.annotations.NotNull;

// TODO: 12/7/2021 implement gson
public class JsonTextAsset implements TextAsset {
    private final Component component;

    JsonTextAsset(@NotNull String json) {
        this.component = GsonComponentSerializer.gson().deserialize(json);
    }

    @Override
    public Component asComponent() {
        return component;
    }
}
