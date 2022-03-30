package com.ehhthan.scholarapi.asset.text;

import com.ehhthan.scholarapi.ScholarBinder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TextAssetFactoryTest {
    private TextAssetFactory factory;
    private Gson gson;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new ScholarBinder(null));
        this.factory = injector.getInstance(TextAssetFactory.class);
        this.gson = injector.getInstance(Gson.class);
    }

    @Test
    void json() {
        String json = "{\"text\":\"hello world\"}";
        TextAsset asset = factory.json(gson.fromJson(json, JsonObject.class));
        String serialized = asset.get();
        assertEquals(json, serialized);
    }

    @Test
    void string() {
        String text = "hello world";
        TextAsset asset = factory.string(text);
        String string = asset.get();
        assertEquals(text, string);
    }
}