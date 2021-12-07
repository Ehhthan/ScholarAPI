package com.ehhthan.scholarapi.asset.file;

import com.google.inject.assistedinject.Assisted;

public interface AssetFileFactory {
    AssetFile assetFile(@Assisted AssetFile.FileType assetType, @Assisted String minecraftPath);
}
