package com.ehhthan.scholarapi.resourcepack;

import com.ehhthan.scholarapi.asset.texture.TextureAssetFactory;
import com.ehhthan.scholarapi.mcmeta.McMetaFactory;
import com.ehhthan.scholarapi.collection.AssetCollectionFactory;
import com.google.inject.Inject;

import java.io.File;

public interface ResourcePackFactory {
    ResourcePack create(File directory);

    class ResourcePackFactoryImpl implements ResourcePackFactory {
        private final McMetaFactory metaFactory;
        private final TextureAssetFactory textureAssetFactory;
        private final AssetCollectionFactory assetCollectionFactory;

        @Inject
        ResourcePackFactoryImpl(McMetaFactory metaFactory, TextureAssetFactory textureAssetFactory, AssetCollectionFactory assetCollectionFactory) {
            this.metaFactory = metaFactory;
            this.textureAssetFactory = textureAssetFactory;
            this.assetCollectionFactory = assetCollectionFactory;
        }

        @Override
        public ResourcePack create(File directory) {
            File mcmeta = new File(directory, "pack.mcmeta");
            File icon = new File(directory, "pack.png");
            File assets = new File(directory, "assets");

            return new ResourcePack.ResourcePackImpl(metaFactory.pack(mcmeta), textureAssetFactory.texture(icon), assetCollectionFactory.create(assets));
        }
    }
}
