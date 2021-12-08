package com.ehhthan.scholarapi.asset.font.character;

import java.awt.image.BufferedImage;

public interface FontCharacterFactory {
    FontCharacter create(int character, BufferedImage texture);

     class FontCharacterFactoryImpl implements FontCharacterFactory {
        @Override
        public FontCharacter create(int character, BufferedImage texture) {
            return new FontCharacterImpl(character, texture);
        }
    }
}
