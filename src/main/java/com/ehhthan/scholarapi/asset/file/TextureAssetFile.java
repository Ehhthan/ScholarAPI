package com.ehhthan.scholarapi.asset.file;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import java.io.File;

public class TextureAssetFile extends AbstractAssetFile {
    @Inject
    TextureAssetFile(@Named("workingDirectory") File directory, @Assisted String minecraftPath) {
        super(directory, "textures", minecraftPath);
    }

    @Override
    public String extension() {
        return ".png";
    }
}
