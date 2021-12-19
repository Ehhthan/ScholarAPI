package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.google.inject.Provider;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Set;

public interface ResourcePack extends Provider<ResourcePack> {
    PackMCMeta meta();

    BufferedImage icon();

    Map<NamespacedKey, FontAsset> fonts();
}
