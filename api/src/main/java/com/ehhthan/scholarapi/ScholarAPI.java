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
class ScholarAPI {
//    private final Map<URI, ResourcePack> packs = new HashMap<>();
//    private final ResourcePackFactory packFactory;
//
//    private ScholarAPI(File file) {
//        Injector injector = Guice.createInjector(new ScholarBinder());
//        this.packFactory = injector.getInstance(ResourcePackFactory.class);
//    }
//
//    public static ScholarAPI get(File file) {
//        return INSTANCE;
//    }
//
//
//    public ResourcePack pack(URI uri) {
//
//    }
    public static void main(String[] args) {
        System.out.println("Hello there fellow scholar.");
    }
}

