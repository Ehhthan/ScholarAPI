package com.ehhthan.scholarapi.asset.font.character;

import com.ehhthan.scholarapi.util.TextureUtil;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.awt.image.BufferedImage;

public final class FontCharacterImpl implements FontCharacter {
    private final char character;
    private final int width, height;

    @Inject
    FontCharacterImpl(@Assisted char character, @Assisted BufferedImage texture) {
        this.character = character;

        texture = TextureUtil.trim(texture);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    @Override
    public char character() {
        return character;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }
}
