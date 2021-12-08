package com.ehhthan.scholarapi.asset.font.provider;

import java.util.Locale;

public interface FontProvider {
    Type type();

    enum Type {
        BITMAP,
        TTF,
        LEGACY_UNICODE;

        private final String path;

        Type() {
            this.path = name().toLowerCase(Locale.ROOT);
        }

        public static Type fromPath(String path) {
            return Type.valueOf(path.toUpperCase(Locale.ROOT));
        }

        public String path() {
            return path;
        }
    }
}
