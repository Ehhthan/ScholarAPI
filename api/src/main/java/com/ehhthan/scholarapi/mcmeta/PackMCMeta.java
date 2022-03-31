package com.ehhthan.scholarapi.mcmeta;

import com.ehhthan.scholarapi.asset.text.TextAsset;

public interface PackMCMeta {
    int format();

    TextAsset description();

    final class PackMCMetaImpl implements PackMCMeta {
        private final int format;
        private final TextAsset description;

        PackMCMetaImpl(int format, TextAsset description) {
            this.format = format;
            this.description = description;
        }

        @Override
        public int format() {
            return format;
        }

        @Override
        public TextAsset description() {
            return description;
        }
    }
}
