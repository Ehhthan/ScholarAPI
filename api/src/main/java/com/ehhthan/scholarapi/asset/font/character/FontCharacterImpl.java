package com.ehhthan.scholarapi.asset.font.character;

public class FontCharacterImpl implements FontCharacter {
    private final int codepoint;
    private final int width, height;

    public FontCharacterImpl(int codepoint, int width, int height) {
        this.codepoint = codepoint;
        this.width = width;
        this.height = height;
    }

    public int codepoint() {
        return codepoint;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
