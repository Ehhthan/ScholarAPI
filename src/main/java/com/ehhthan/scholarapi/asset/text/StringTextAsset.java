package com.ehhthan.scholarapi.asset.text;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class StringTextAsset implements TextAsset {
    private final Component component;

    StringTextAsset(@NotNull String text) {
        this.component = Component.text(text);
    }

    @Override
    public Component asComponent() {
        return component;
    }
}
