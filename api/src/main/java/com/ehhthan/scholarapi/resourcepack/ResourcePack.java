package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.ehhthan.scholarapi.collection.AssetCollection;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface ResourcePack {
    PackMCMeta meta();

    BufferedImage icon();

    Map<String, AssetCollection> assets();

    final class ResourcePackImpl implements ResourcePack {
//    private static final Pattern NAMESPACE_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+)");

        private final PackMCMeta meta;
        private final BufferedImage icon;

        private final Map<String, AssetCollection> assets;

        ResourcePackImpl(PackMCMeta mcMeta, BufferedImage icon, Map<String, AssetCollection> assets) {
            this.meta = mcMeta;
            this.icon = icon;
            this.assets = assets;
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

        @Override
        public Map<String, AssetCollection> assets() {
            return assets;
        }
    }

}
