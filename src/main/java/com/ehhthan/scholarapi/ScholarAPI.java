package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
//        File localTestDirectory = new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\resourcepacks\\MMOBars-Pack-2.2\\assets");
//        Injector injector = Guice.createInjector(new ScholarBinder(localTestDirectory));
//
//        NamespacedKeyFactory factory = injector.getInstance(NamespacedKeyFactory.class);
//
//        ResourcePack pack = injector.getInstance(ResourcePack.class);
//
//        FontAsset asset = pack.fonts().get(factory.minecraftPath("mmobars:ab_0"));
//
//        System.out.println(pack.fonts().keySet());
//        asset.getCharacter('\u041c');
    }
}

