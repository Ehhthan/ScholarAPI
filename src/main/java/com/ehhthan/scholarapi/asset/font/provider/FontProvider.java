package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface FontProvider {
    List<FontCharacter> fontCharacters();

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
