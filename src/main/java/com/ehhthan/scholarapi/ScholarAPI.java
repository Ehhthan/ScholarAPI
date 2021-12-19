package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKeyFactory;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ScholarAPI {
    private static ScholarAPI INSTANCE;
    private final ResourcePack resourcePack;

    ScholarAPI(File resourcesDirectory) {
        Injector injector = Guice.createInjector(new ScholarBinder(resourcesDirectory));

        this.resourcePack = injector.getInstance(ResourcePack.class);
    }

    public static ScholarAPI getInstance(File resourcesDirectory) throws ZipException {
        ZipFile file = new ZipFile(resourcesDirectory);
        String path = resourcesDirectory.getPath() + "ScholarAPI";
        file.extractAll(path);
        return (INSTANCE != null) ? INSTANCE : new ScholarAPI(new File(path, "assets"));
    }

    public ResourcePack getPack() {
        return resourcePack;
    }
}

