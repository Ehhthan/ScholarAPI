package com.ehhthan.scholarapi.ztest;

import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.texture.TextureAsset;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.util.Map;
import java.util.Set;

// TODO: 12/20/2021 implement everything
public interface AssetCollection {
    String namespace();

    Set<FontAsset> fonts();

    Set<TextureAsset> textures();

    static Builder create(String namespace) {
        return new AssetCollectionImpl.BuilderImpl(namespace);
    }

    interface Builder {
        Builder fonts(Set<FontAsset> fontAssets);

        Builder textures(Set<TextureAsset> textureAssets);

        AssetCollection build();
    }
}
