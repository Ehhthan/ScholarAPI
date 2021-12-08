package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;
import java.util.Arrays;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
        get(new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\versions\\1.17.1\\assets"));
    }

    public static void get(File workingDirectory) {
        Injector injector = Guice.createInjector(new ScholarBinder(workingDirectory));

        AssetFileFactory instance = injector.getInstance(AssetFileFactory.class);

        NamespacedKey namespacedKey = injector.getInstance(NamespacedKeyFactory.class).namespacedKey("minecraft:default");

        FontAsset font = injector.getInstance(FontAssetFactory.class).create(instance.font(namespacedKey));
        for (FontProvider provider : font.providers()) {
            if (provider instanceof BitmapFontProvider) {
                int codepoint = Character.toCodePoint('\ud83c','\udf0a');
                if (((BitmapFontProvider) provider).charMap().containsKey(codepoint)) {
                    FontCharacter fontChar = ((BitmapFontProvider) provider).charMap().get(codepoint);
                    System.out.println("codepoint -> " + fontChar.codepoint());
                    System.out.println("character -> " + String.valueOf(fontChar.character()));
                    System.out.println("width -> " + fontChar.width());
                    System.out.println("height -> " + fontChar.height());
                }
            }
        }

    }
}

