package com.ehhthan.scholarapi.asset.file;

import com.google.inject.name.Named;

import java.io.File;
import java.util.Locale;

public interface AssetFile {
    String extension();

    File asFile();

    String asMinecraftPath();
}
