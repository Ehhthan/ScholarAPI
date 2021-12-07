package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.file.FontAssetFile;

public interface FontAssetFactory {
    FontAsset create(FontAssetFile file);
}
