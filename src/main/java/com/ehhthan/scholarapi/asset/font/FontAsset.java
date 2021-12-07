package com.ehhthan.scholarapi.asset.font;

import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.location.NamespacedKey;

public interface FontAsset {
    NamespacedKey namespacedKey();

    FontProvider[] providers();
}
