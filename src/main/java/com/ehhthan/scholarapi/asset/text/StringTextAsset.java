package com.ehhthan.scholarapi.asset.text;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class StringTextAsset implements TextAsset {
    private final Component component;

    @Inject
    StringTextAsset(@Assisted @NotNull String text) {
        this.component = Component.text(text);
    }

    @Override
    public Component asComponent() {
        return component;
    }
}
