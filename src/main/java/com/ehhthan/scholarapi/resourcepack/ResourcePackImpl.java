package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class ResourcePackImpl implements ResourcePack {
    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+)");

    private final PackMCMeta meta;
    private final BufferedImage icon;

    private final transient Set<String> namespaces = new HashSet<>();

    private final transient Map<NamespacedKey, FontAsset> fonts = new HashMap<>();

    @Inject
    ResourcePackImpl(@Named("workingDirectory") File workingDirectory, AssetFileFactory fileFactory, FontAssetFactory fontFactory) {
        this.meta = null;
        this.icon = null;

        for (File file : workingDirectory.listFiles()) {
            if (file.isDirectory() && NAMESPACE_PATTERN.matcher(file.getName()).matches())
                namespaces.add(file.getName());
        }

        for (String namespace : namespaces) {
            File fontFolder = new File(workingDirectory, namespace + "/font/");
            if (fontFolder.exists() && fontFolder.isDirectory())
                for (File file : fontFolder.listFiles(AssetFile.Type.FONT.filter())) {
                    FontAsset asset = fontFactory.file(fileFactory.font(file));
                    fonts.put(asset.namespacedKey(), asset);
                }
        }
    }

    @Override
    public PackMCMeta meta() {
        return meta;
    }

    @Override
    public BufferedImage icon() {
        return icon;
    }

    @Override
    public Map<NamespacedKey, FontAsset> fonts() {
        return fonts;
    }

    @Override
    public ResourcePack get() {
        return null;
    }
}
