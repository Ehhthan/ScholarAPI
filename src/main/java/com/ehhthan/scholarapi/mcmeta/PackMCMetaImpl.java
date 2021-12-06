package com.ehhthan.scholarapi.mcmeta;

import com.ehhthan.scholarapi.asset.text.TextAsset;
import com.google.inject.Inject;

public class PackMCMetaImpl implements PackMCMeta {
    private int packFormat;
    private TextAsset description;

    @Inject
    PackMCMetaImpl(int packFormat, TextAsset description) {
        this.packFormat = packFormat;
        this.description = description;
    }

    @Override
    public int packFormat() {
        return packFormat;
    }

    @Override
    public TextAsset description() {
        return description;
    }
}
