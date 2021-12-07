package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.file.TextureAssetFile;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
        get(new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\versions\\1.17.1\\assets"));
    }

    public static void get(File workingDirectory) {
        String mcPath = "minecraft:block/button";
        Injector injector = Guice.createInjector(new ScholarBinder(workingDirectory));

        String json = "{ \"type\": \"bitmap\", \"file\": \"mmobars:font/solid/bar_1.png\", \"height\": 16, \"ascent\": 0, \"chars\": [\"\\uE000\",\"\\uE001\",\"\\uE002\",\"\\uE003\",\"\\uE004\",\"\\uE005\",\"\\uE006\",\"\\uE007\",\"\\uE008\",\"\\uE009\",\"\\uE00A\",\"\\uE00B\",\"\\uE00C\",\"\\uE00D\",\"\\uE00E\",\"\\uE00F\"] }";

        BitmapFontProvider bitmap = injector.getInstance(FontProviderFactory.class).bitmap(JsonParser.parseString(json).getAsJsonObject());
        FontCharacter character = bitmap.character('\uE005');
        System.out.println("width -> " + character.width());
        System.out.println("height -> " + character.height());
    }
}

