package com.ehhthan.scholarapi.asset.file;

import com.ehhthan.scholarapi.location.NamespacedKey;

import java.io.File;
import java.io.FileFilter;
import java.util.Locale;

public interface AssetFile {
    Type type();

    File asFile();

    NamespacedKey asNamespacedKey();

     enum Type {
        TEXTURE("textures", ".png"),
        MODEL("models", ".json"),
        FONT(".json");

        private final String path;
        private final String extension;

        Type(String extension) {
            this.path = name().toLowerCase(Locale.ROOT);
            this.extension = extension;
        }

        Type(String path, String extension) {
            this.path = path;
            this.extension = extension;
        }

        public String path() {
            return path;
        }

        public String extension() {
            return extension;
        }

        public FileFilter filter() {
            return file -> file.getName().endsWith(extension);
        }
    }
}
