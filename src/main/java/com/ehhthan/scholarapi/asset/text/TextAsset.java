package com.ehhthan.scholarapi.asset.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public interface TextAsset {
    Component asComponent();

    default String asJson() {
        return GsonComponentSerializer.gson().serialize(asComponent());
    }

    default String asString() {
        return PlainTextComponentSerializer.plainText().serialize(asComponent());
    }
}
