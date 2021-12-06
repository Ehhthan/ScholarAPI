package com.ehhthan.scholarapi.asset.font.character;

public interface FontCharacter {
    char character();

    int width();

    int height();

    // TODO: 12/6/2021 rename these better? uncropped height + width
    int imageWidth();

    int imageHeight();
}
