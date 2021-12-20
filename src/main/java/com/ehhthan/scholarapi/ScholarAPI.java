package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Guice;
import com.google.inject.Injector;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class ScholarAPI {
    private static ScholarAPI INSTANCE;
    private final ResourcePack resourcePack;

    ScholarAPI(File resourcesDirectory) {
        Injector injector = Guice.createInjector(new ScholarBinder(resourcesDirectory));

        this.resourcePack = injector.getInstance(ResourcePack.class);
    }

    public static ScholarAPI getInstance(File resourcesDirectory) throws ZipException {
        if (INSTANCE == null)
            INSTANCE = new ScholarAPI(resourcesDirectory);
        return INSTANCE;
    }

    public ResourcePack getPack() {
        return resourcePack;
    }
}

