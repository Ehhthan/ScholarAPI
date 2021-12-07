package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.google.inject.Inject;

import java.awt.image.BufferedImage;

public final class ResourcePackImpl implements ResourcePack {
    private final PackMCMeta meta;
    private final BufferedImage icon;

    @Inject
    ResourcePackImpl(PackMCMeta meta) {
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
