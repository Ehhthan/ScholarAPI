package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.google.inject.Inject;

import java.awt.image.BufferedImage;

public class ResourcePackImpl implements ResourcePack {
    private PackMCMeta meta;
    private BufferedImage icon;

    @Inject
    public ResourcePackImpl(PackMCMeta meta) {
        this.meta = meta;
        this.icon = null;
    }

    @Override
    public PackMCMeta meta() {
        return meta;
    }

    @Override
    public BufferedImage icon() {
        return icon;
    }
}
