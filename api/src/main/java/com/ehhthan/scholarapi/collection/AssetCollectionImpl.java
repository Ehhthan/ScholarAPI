package com.ehhthan.scholarapi.collection;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.texture.TextureAsset;

import java.util.Set;

class AssetCollectionImpl implements AssetCollection {
    private final String namespace;

    private final Set<FontAsset> fonts;
    private final Set<TextureAsset> textures;

    private AssetCollectionImpl(BuilderImpl builder) {
        this.namespace = builder.namespace;

        this.fonts = builder.fonts;
        this.textures = builder.textures;
    }


    @Override
    public String namespace() {
        return namespace;
    }

    @Override
    public Set<FontAsset> fonts() {
        return fonts;
    }

    @Override
    public Set<TextureAsset> textures() {
        return textures;
    }

    static class BuilderImpl implements AssetCollection.Builder {
        private final String namespace;

        private Set<FontAsset> fonts;
        private Set<TextureAsset> textures;

        public BuilderImpl(String namespace) {
            this.namespace = namespace;
        }

        @Override
        public Builder fonts(Set<FontAsset> fontAssets) {
            this.fonts = fontAssets;
            return this;
        }

        @Override
        public Builder textures(Set<TextureAsset> textureAssets) {
            this.textures = textureAssets;
            return this;
        }

        @Override
        public AssetCollection build() {
            return new AssetCollectionImpl(this);
        }
    }
}
