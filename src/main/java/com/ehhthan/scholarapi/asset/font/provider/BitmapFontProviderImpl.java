package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.file.TextureAssetFile;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class BitmapFontProviderImpl implements BitmapFontProvider {
    private final TextureAssetFile file;
    private final int height;
    private final int ascent;
    private final String[] chars;

    private final transient Map<Character, FontCharacter> characterMap = new HashMap<>();

    @Inject
    BitmapFontProviderImpl(FontCharacterFactory characterFactory, AssetFileFactory fileFactory, @Assisted JsonObject json) {
        this.file = fileFactory.texture(json.getAsJsonPrimitive("file").getAsString());
        this.height = json.has("height") ? json.getAsJsonPrimitive("height").getAsInt() : 8;
        this.ascent = json.getAsJsonPrimitive("ascent").getAsInt();

        JsonArray strings = json.getAsJsonArray("chars");
        this.chars = new String[strings.size()];
        for(int i = 0; i < strings.size(); i++){
            chars[i] = strings.get(i).getAsString();
        }

        int rows = this.chars.length;
        int columns = this.chars[0].toCharArray().length;

        char[][] sortedChars = new char[rows][columns];
        for (int i = 0; i < rows; i++)
            sortedChars[i] = chars[i].toCharArray();

        try {
            BufferedImage texture = ImageIO.read(file.asFile());

            int tileWidth = texture.getWidth() / columns;
            int tileHeight = texture.getHeight() / rows;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    char character = sortedChars[i][j];
                    if (character != 0) {
                        characterMap.put(character, characterFactory.create(character, texture.getSubimage(tileWidth * j, tileHeight * i, tileWidth, tileHeight)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public TextureAssetFile file() {
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
    public FontCharacter character(char c) {
        return characterMap.get(c);
    }

    @Override
    public boolean hasCharacter(char c) {
        return characterMap.containsKey(c);
    }
}
