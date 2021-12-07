package com.ehhthan.scholarapi.asset.file;

import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import java.io.File;

public class ModelAssetFile extends AbstractAssetFile {
    @Inject
    ModelAssetFile(NamespacedKeyFactory namespacedKeyFactory, @Named("workingDirectory") File directory, @Assisted String minecraftPath) {
        super(namespacedKeyFactory, directory, "models", minecraftPath);
    }

    @Override
    public String extension() {
        return ".json";
    }
}
