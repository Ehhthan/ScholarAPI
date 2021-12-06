package com.ehhthan.scholarapi.asset.file;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import java.io.File;
import java.nio.file.Paths;

public class TextureAssetFile implements AssetFile {
    private File directory;
    private String file;

    @Inject
    TextureAssetFile(@Named("workingDirectory") File directory, @Assisted String file) {
        this.directory = directory;
        this.file = file;

    }

    @Override
    public File asFile() {
        return null;
    }

    @Override
    public String asString() {
        return null;
    }
}
