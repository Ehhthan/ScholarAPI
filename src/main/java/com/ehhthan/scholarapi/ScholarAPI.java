package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterImpl;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
        File localTestDirectory = new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\versions\\1.18\\assets");
        Injector injector = Guice.createInjector(new ScholarBinder(localTestDirectory));

        NamespacedKeyFactory factory = injector.getInstance(NamespacedKeyFactory.class);

        //ResourcePack pack = injector.getInstance(ResourcePack.class);

        //FontAsset asset = pack.fonts().get(factory.minecraftPath("minecraft:default"));

        FontAsset asset = injector.getInstance(FontAssetFactory.class).file(new File(localTestDirectory, "minecraft/font/default.json"));

        FontCharacter cur = asset.getCharacter(']');

        System.out.println("char -> " + Character.toString(cur.codepoint()));
        System.out.println("codepoint -> " + cur.codepoint());
        System.out.println("width -> " + cur.width());
        System.out.println("height -> " + cur.height());
    }
}

