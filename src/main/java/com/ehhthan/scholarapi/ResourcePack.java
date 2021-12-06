package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.mcmeta.PackMCMeta;

import java.awt.image.BufferedImage;

public interface ResourcePack {
    PackMCMeta meta();

    BufferedImage icon();
}
