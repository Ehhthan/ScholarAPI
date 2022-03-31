package com.ehhthan.scholarapi.mcmeta;

import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
import com.google.inject.Inject;

import java.io.File;


// TODO: 3/31/2022 implement properly
public interface McMetaFactory {
    PackMCMeta pack(File file);

    class McMetaFactoryImpl implements McMetaFactory {

        private final TextAssetFactory textAssetFactory;

        @Inject
        public McMetaFactoryImpl(TextAssetFactory textAssetFactory) {
            this.textAssetFactory = textAssetFactory;
        }

        @Override
        public PackMCMeta pack(File file) {
            return new PackMCMeta.PackMCMetaImpl(8, textAssetFactory.string("test"));
        }
    }
}
