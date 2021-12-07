package com.ehhthan.scholarapi.asset.text;

import com.ehhthan.scholarapi.ScholarBinder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.kyori.adventure.text.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TextAssetFactoryTest {
    private TextAssetFactory factory;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new ScholarBinder(
            new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\resourcepacks\\MMOBars-Pack-2.2\\assets")));
        this.factory = injector.getInstance(TextAssetFactory.class);
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