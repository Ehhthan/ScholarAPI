package com.ehhthan.scholarapi.asset.text;

import org.jetbrains.annotations.NotNull;

public interface TextAsset {
    String get();

    class TextAssetImpl implements TextAsset {
        private final String text;

        TextAssetImpl(@NotNull String text) {
            this.text = text;
        }

        @Override
        public String get() {
            return text;
        }
    }
}
