package com.ehhthan.scholarapi.asset.file;

import com.google.inject.name.Named;

import java.io.File;
import java.util.Locale;

public interface AssetFile {
    FileType type();

    File asFile();

    String asMinecraftPath();

    enum FileType {
        BLOCKSTATES,
        FONT,
        LANG,
        MODELS,
        PARTICLES,
        SHADERS,
        TEXTS,
        TEXTURES;

        public String path() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}
