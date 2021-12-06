package com.ehhthan.scholarapi.asset.font.provider;

import java.util.Locale;

public interface FontProvider {
    ProviderType type();

    enum ProviderType {
        BITMAP,
        TTF,
        LEGACY_UNICODE;

        public String asString() {
            return (name().toLowerCase(Locale.ROOT));
        }
    }
}
