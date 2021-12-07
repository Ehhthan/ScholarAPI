package com.ehhthan.scholarapi.asset.font.character;

import java.awt.image.BufferedImage;

public interface FontCharacterFactory {
    FontCharacter create(char character, BufferedImage texture);
}
