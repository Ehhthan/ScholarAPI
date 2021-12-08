package com.ehhthan.scholarapi.asset.font.provider;

import com.google.gson.JsonObject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public interface FontProviderFactory {
    @Named("bitmap") BitmapFontProvider bitmap(@Assisted JsonObject json);
}
