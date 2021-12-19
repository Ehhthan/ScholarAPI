package com.ehhthan.scholarapi.asset.font.character;

import com.ehhthan.scholarapi.asset.texture.LoneTextureAsset;
import com.ehhthan.scholarapi.asset.texture.TiledTextureAsset;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public interface FontCharacterFactory {
    FontCharacter texture(int codepoint, LoneTextureAsset texture);

    List<FontCharacter> tiled(int[][] codepoints, TiledTextureAsset texture);

    List<FontCharacter> tiled(String[] chars, TiledTextureAsset texture);

    class FontCharacterFactoryImpl implements FontCharacterFactory {
        @Override
        public FontCharacter texture(int codepoint, LoneTextureAsset texture) {
            return new FontCharacterImpl(codepoint, texture.texture().getWidth(), texture.texture().getHeight());
        }

        @Override
        public List<FontCharacter> tiled(int[][] codepoints, TiledTextureAsset texture) {
            List<FontCharacter> fontCharacters = new ArrayList<>();
            for (int i = 0; i < codepoints.length; i++) {
                int[] column = codepoints[i];
                for (int j = 0; j < column.length; j++) {
                    int point = column[j];
                    if (point != 0) {
                        BufferedImage image = texture.textures()[i][j];
                        fontCharacters.add(new FontCharacterImpl(point, image.getWidth(), image.getHeight()));
                    }
                }
            }
            return fontCharacters;
        }

        @Override
        public List<FontCharacter> tiled(String[] chars, TiledTextureAsset texture) {
            int[][] codepoints = new int[chars.length][];

            for (int i = 0; i < chars.length; i++) {
                codepoints[i] = chars[i].codePoints().toArray();
            }

            return tiled(codepoints, texture);
        }
    }
}
