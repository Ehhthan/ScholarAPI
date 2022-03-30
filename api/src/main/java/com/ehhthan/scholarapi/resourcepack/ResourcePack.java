package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface ResourcePack {
    PackMCMeta meta();

    BufferedImage icon();
}
