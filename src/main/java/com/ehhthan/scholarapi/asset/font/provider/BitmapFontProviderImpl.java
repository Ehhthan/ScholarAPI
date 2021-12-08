package com.ehhthan.scholarapi.asset.font.provider;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.common.base.Preconditions;
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
    private final AssetFile file;
    private final int height;
    private final int ascent;
    private final String[] chars;

    private final transient Map<Integer, FontCharacter> charMap = new HashMap<>();

    @Inject
    BitmapFontProviderImpl(FontCharacterFactory characterFactory,
                           AssetFileFactory fileFactory,
                           NamespacedKeyFactory namespacedKeyFactory,
                           @Assisted JsonObject json) {

        this.file = fileFactory.texture(namespacedKeyFactory.namespacedKey(json.getAsJsonPrimitive("file").getAsString()));

        Preconditions.checkArgument(file.type() == AssetFile.Type.TEXTURE, "Asset is not a texture.");

        this.height = json.has("height") ? json.getAsJsonPrimitive("height").getAsInt() : 8;
        this.ascent = json.getAsJsonPrimitive("ascent").getAsInt();

        JsonArray strings = json.getAsJsonArray("chars");
        this.chars = new String[strings.size()];
        for(int i = 0; i < strings.size(); i++){
            chars[i] = strings.get(i).getAsString();
        }

        int rows = this.chars.length;
        int columns = this.chars[0].toCharArray().length;

        int[][] codepoints = new int[rows][columns];
        for (int i = 0; i < rows; i++)
            codepoints[i] = chars[i].codePoints().toArray();

        try {
            BufferedImage texture = ImageIO.read(file.asFile());

            int tileWidth = texture.getWidth() / columns;
            int tileHeight = texture.getHeight() / rows;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    int codepoint = codepoints[i][j];
                    if (codepoint != 0) {
                        charMap.put(codepoint, characterFactory.create(codepoint, texture.getSubimage(tileWidth * j, tileHeight * i, tileWidth, tileHeight)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AssetFile file() {
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
    public Map<Integer, FontCharacter> fontCharacters() {
        return charMap;
    }
}
