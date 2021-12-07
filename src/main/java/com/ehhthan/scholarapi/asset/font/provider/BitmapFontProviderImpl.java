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

    private final Map<Character, FontCharacter> characterMap = new HashMap<>();

    @Inject
    BitmapFontProviderImpl(FontCharacterFactory characterFactory, AssetFileFactory fileFactory, @Assisted JsonObject json) {
        this.file = fileFactory.textureFile(json.getAsJsonPrimitive("file").getAsString());
        this.height = json.getAsJsonPrimitive("height").getAsInt();
        this.ascent = json.getAsJsonPrimitive("ascent").getAsInt();

        JsonArray array = json.getAsJsonArray("chars");
        this.chars = new String[array.size()];

        // TODO: 12/7/2021 optimize character caches
        for(int i = 0; i < array.size(); i++){
            chars[i] = array.get(i).getAsString();
        }

        char[][] charsArray = new char[this.chars.length][this.chars[0].toCharArray().length];
        for (int i = 0; i < this.chars.length; i++) {
            char[] loopArray = this.chars[i].toCharArray();
            System.arraycopy(loopArray, 0, charsArray[i], 0, loopArray.length);
        }

        try {
            BufferedImage texture = ImageIO.read(file.asFile());
            int rows = this.chars[0].toCharArray().length;
            int columns = this.chars.length;
            int tileWidth = texture.getWidth() / rows;
            int tileHeight = texture.getHeight() / columns;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    BufferedImage tile = texture.getSubimage(tileWidth * i, tileHeight * j, tileWidth, tileHeight);

                    char c = charsArray[j][i];
                    if (c != 0) {
                        characterMap.put(c, characterFactory.create(c, tile));
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
}
