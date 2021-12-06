package com.ehhthan.scholarapi.asset.text;

import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextAssetFactoryTest {
    private TextAssetFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = new TextAssetFactoryImpl();
    }

    @Test
    void jsonAsset() {
        String json = "{\"text\":\"hello world\"}";
        TextAsset asset = factory.jsonAsset(json);
        String serialized = asset.asJson();
        assertEquals(json, serialized);
    }

    @Test
    void stringAsset() {
        String text = "hello world";
        TextAsset asset = factory.stringAsset(text);
        String string = asset.asString();
        assertEquals(text, string);
    }
}