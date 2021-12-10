package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;
import java.util.Arrays;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
        File localTestDirectory = new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\versions\\1.18\\assets");
        Injector injector = Guice.createInjector(new ScholarBinder(localTestDirectory));

        NamespacedKeyFactory factory = injector.getInstance(NamespacedKeyFactory.class);

        ResourcePack pack = injector.getInstance(ResourcePack.class);

        FontAsset asset = pack.fonts().get(factory.minecraftPath("minecraft:default"));

        FontCharacter cur = asset.getCharacter('l');

        System.out.println("char -> " + Arrays.toString(cur.character()));
        System.out.println("codepoint -> " + cur.codepoint());
        System.out.println("width -> " + cur.width());
        System.out.println("height -> " + cur.height());
    }
}

