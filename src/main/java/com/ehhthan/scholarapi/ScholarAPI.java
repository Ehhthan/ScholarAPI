package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKeyFactory;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;

@Singleton
public class ScholarAPI {
    private static ScholarAPI INSTANCE;
    private final ResourcePack resourcePack;

    ScholarAPI(File resourcesDirectory) {
        Injector injector = Guice.createInjector(new ScholarBinder(resourcesDirectory));

        this.resourcePack = injector.getInstance(ResourcePack.class);
    }

    public static ScholarAPI getInstance(File resourcesDirectory) {
        return (INSTANCE != null) ? INSTANCE : new ScholarAPI(resourcesDirectory);
    }

    public ResourcePack getPack() {
        return resourcePack;
    }
}

