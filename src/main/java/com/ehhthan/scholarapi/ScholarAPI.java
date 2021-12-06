package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
        get(new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\resourcepacks\\MMOBars-Pack-2.2\\assets"));
    }

    public static void get(File workingDirectory) {
        Injector injector = Guice.createInjector(new ScholarBinder(workingDirectory));
        System.out.println(injector.getInstance(NamespacedKeyFactory.class).namespacedKey("minecraft", "item"));
    }
}

