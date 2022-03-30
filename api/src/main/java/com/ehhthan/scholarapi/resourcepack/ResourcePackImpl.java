package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.asset.AssetLocation;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.file.ResourcesDirectory;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.ehhthan.scholarapi.file.JsonFileFilter;
import com.google.inject.Inject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class ResourcePackImpl implements ResourcePack {
//    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+)");

    private final PackMCMeta meta;
    private final BufferedImage icon;

    @Inject
    ResourcePackImpl(@ResourcesDirectory File workingDirectory, FontAssetFactory fontFactory, JsonFileFilter filter) {
        this.meta = null;
        this.icon = null;
//
//        Set<String> namespaces = new HashSet<>();
//        File[] folders = workingDirectory.listFiles();
//        if (folders != null)
//            for (File file : folders) {
//                if (file.isDirectory() && NAMESPACE_PATTERN.matcher(file.getName()).matches())
//                    namespaces.add(file.getName());
//            }

//        for (String namespace : namespaces) {
//            File fontFolder = new File(workingDirectory, AssetLocation.FONT.path(namespace));
//            if (fontFolder.exists() && fontFolder.isDirectory()) {
//                File[] files = fontFolder.listFiles(filter);
//                if (files != null)
//                    for (File file : files) {
//                        FontAsset asset = fontFactory.file(file);
//                        fonts.put(asset.namespacedKey(), asset);
//                    }
//            }
//        }
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
