package com.ehhthan.scholarapi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.io.File;

@Singleton
public class ScholarAPI {
    public static void main(String[] args) {
        File localTestDirectory = new File("C:\\Users\\Ethan\\AppData\\Roaming\\.minecraft\\versions\\1.17.1\\assets");
        Injector injector = Guice.createInjector(new ScholarBinder(localTestDirectory));
    }
}

