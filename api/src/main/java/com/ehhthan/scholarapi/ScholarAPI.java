package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.ehhthan.scholarapi.resourcepack.ResourcePackFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ScholarAPI {
    private static ScholarAPI INSTANCE;

    private final Map<File, ResourcePack> packs = new HashMap<>();
    private final ResourcePackFactory packFactory;

    private ScholarAPI() {
        Injector injector = Guice.createInjector(new ScholarBinder());
        this.packFactory = injector.getInstance(ResourcePackFactory.class);
    }

    public static ScholarAPI get() {
        if (INSTANCE == null)
            INSTANCE = new ScholarAPI();
        return INSTANCE;
    }

    public ResourcePack pack(File file) {
        return packs.computeIfAbsent(file, packFactory::create);
    }
}

