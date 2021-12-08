package com.ehhthan.scholarapi.asset.font.character;

import com.ehhthan.scholarapi.util.TextureUtil;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import java.awt.image.BufferedImage;

public final class FontCharacterImpl implements FontCharacter {
    private final int codepoint;
    private final int width, height;

    private final char[] character;

    @Inject
    FontCharacterImpl(@Assisted int codepoint, @Assisted BufferedImage texture) {
        this.codepoint = codepoint;

        texture = TextureUtil.trim(texture);
        this.width = texture.getWidth();
        this.height = texture.getHeight();

        StringBuilder sb = new StringBuilder();
        if (Character.isBmpCodePoint(codepoint)) {
            sb.append((char) codepoint);
        } else if (Character.isValidCodePoint(codepoint)) {
            sb.append(Character.highSurrogate(codepoint))
            .append(Character.lowSurrogate(codepoint));
        }
        this.character = sb.toString().toCharArray();
    }

    @Override
    public int codepoint() {
        return codepoint;
    }

    public char[] character() {
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
