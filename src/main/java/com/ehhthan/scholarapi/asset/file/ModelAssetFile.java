package com.ehhthan.scholarapi.asset.file;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import java.io.File;

public class ModelAssetFile extends AbstractAssetFile {
    @Inject
    ModelAssetFile(@Named("workingDirectory") File directory, @Assisted String minecraftPath) {
        super(directory, "models", minecraftPath);
    }

    @Override
    public String extension() {
        return ".json";
    }
}
