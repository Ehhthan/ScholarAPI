package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.file.FontAssetFile;
import com.ehhthan.scholarapi.asset.file.TextureAssetFile;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
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

        AssetFileFactory instance = injector.getInstance(AssetFileFactory.class);

        FontAsset font = injector.getInstance(FontAssetFactory.class).create(instance.font("mmobars:ab_0"));
        for (FontProvider provider : font.providers()) {
            if (provider instanceof BitmapFontProvider) {
                char c = '\uE327';
                if (((BitmapFontProvider) provider).hasCharacter(c)) {
                    FontCharacter fontChar = ((BitmapFontProvider) provider).character(c);
                    System.out.println("char -> " + fontChar.character());
                    System.out.println("width -> " + fontChar.width());
                    System.out.println("height -> " + fontChar.height());
                }
            }
        }

    }
}

