package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface ResourcePack {
    PackMCMeta meta();

    BufferedImage icon();

    Map<NamespacedKey, FontAsset> fonts();

    default boolean isFont(NamespacedKey namespacedKey) {
        return fonts().containsKey(namespacedKey);
    }

    default FontAsset font(NamespacedKey namespacedKey) {
        return fonts().get(namespacedKey);
    }
}
