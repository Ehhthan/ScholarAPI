package com.ehhthan.scholarapi.file;

import com.google.inject.Singleton;

import java.io.File;
import java.io.FileFilter;

@Singleton
public class JsonFileFilter implements FileFilter {
    private static final String EXTENSION = ".json";

    @Override
    public boolean accept(File file) {
        return file.getName().endsWith(EXTENSION);
    }
}
