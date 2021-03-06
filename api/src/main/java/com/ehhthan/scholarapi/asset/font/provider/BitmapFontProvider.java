package com.ehhthan.scholarapi.asset.font.provider;


import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;

import java.util.List;

public interface BitmapFontProvider extends FontProvider {
    NamespacedKey file();

    int height();

    int ascent();

    String[] chars();

    final class BitmapFontProviderImpl implements BitmapFontProvider {
        private final NamespacedKey file;
        private final int height;
        private final int ascent;
        private final String[] chars;

        private final List<FontCharacter> fontCharacters;

        BitmapFontProviderImpl(NamespacedKey file, int height, int ascent, String[] chars, List<FontCharacter> fontCharacters) {
            this.file = file;
            this.height = height;
            this.ascent = ascent;
            this.chars = chars;

            this.fontCharacters = fontCharacters;
        }

        @Override
        public NamespacedKey file() {
            return file;
        }

        @Override
        public int height() {
            return height;
        }

        @Override
        public int ascent() {
            return ascent;
        }

        @Override
        public String[] chars() {
            return chars;
        }

        @Override
        public List<FontCharacter> fontCharacters() {
            return fontCharacters;
        }
    }
}
