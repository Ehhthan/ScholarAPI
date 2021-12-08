package com.ehhthan.scholarapi.mcmeta;

import com.ehhthan.scholarapi.asset.text.TextAsset;
import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;

public final class PackMCMetaProvider implements Provider<PackMCMeta> {
    private final TextAssetFactory textAssetFactory;

    @Inject
     PackMCMetaProvider(TextAssetFactory textAssetFactory) {
        this.textAssetFactory = textAssetFactory;
    }

    @Override
    public PackMCMeta get() {
        int packFormat = 8;
        TextAsset textAsset = textAssetFactory.stringAsset("ScholarAPI pack.");
        return new PackMCMetaImpl(packFormat, textAsset);
    }
}
