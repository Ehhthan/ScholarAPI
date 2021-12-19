package com.ehhthan.scholarapi.asset.texture;

import com.ehhthan.scholarapi.asset.AssetLocation;
import com.ehhthan.scholarapi.file.ResourcesDirectory;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKey;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public interface TextureAssetFactory {
    LoneTextureAsset texture(BufferedImage texture);

    LoneTextureAsset texture(File file);

    LoneTextureAsset texture(NamespacedKey namespacedKey);

    TiledTextureAsset tiled(int rows, int columns, BufferedImage texture);

    TiledTextureAsset tiled(int rows, int columns, File file);

    TiledTextureAsset tiled(int rows, int columns, NamespacedKey namespacedKey);

    class TextureAssetFactoryImpl implements TextureAssetFactory {
        private final File workingDirectory;

        @Inject
        public TextureAssetFactoryImpl(@ResourcesDirectory File workingDirectory) {
            this.workingDirectory = workingDirectory;
        }


        @Override
        public LoneTextureAsset texture(BufferedImage texture) {
            return new LoneTextureAsset(crop(texture));
        }

        @Override
        public LoneTextureAsset texture(File file) {
            Preconditions.checkArgument(file.exists(), "File does not exist: %s", file.getPath());

            // TODO: 12/12/2021 this can probably be done much better
            try {
                return texture(ImageIO.read(file));
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid texture file: " + file.getPath());
            }
        }

        @Override
        public LoneTextureAsset texture(NamespacedKey namespacedKey) {
            File file = new File(workingDirectory, AssetLocation.TEXTURES.path(namespacedKey));
            return texture(file);
        }

        // TODO: 12/16/2021 test this
        @Override
        public TiledTextureAsset tiled(int rows, int columns, BufferedImage texture) {
            BufferedImage[][] textures = new BufferedImage[rows][columns];

            int tileWidth = texture.getWidth() / columns;
            int tileHeight = texture.getHeight() / rows;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    textures[i][j] = crop(texture.getSubimage(tileWidth * j, tileHeight * i, tileWidth, tileHeight));
                }
            }

            return new TiledTextureAsset(textures);
        }

        @Override
        public TiledTextureAsset tiled(int rows, int columns, File file) {
            Preconditions.checkArgument(file.exists(), "File does not exist: %s", file.getPath());

            // TODO: 12/12/2021 this can probably be done much better
            try {
                return tiled(rows, columns, ImageIO.read(file));
            } catch (IOException e) {
                throw new IllegalArgumentException("Invalid texture file: " + file.getPath());
            }
        }

        @Override
        public TiledTextureAsset tiled(int rows, int columns, NamespacedKey namespacedKey) {
            File file = new File(workingDirectory, AssetLocation.TEXTURES.path(namespacedKey));
            return tiled(rows, columns, file);
        }

        /**
         * Crops a texture.
         * @author Oleg Mikhailov
         * @param texture texture to be cropped
         * @return cropped texture
         */
        private static BufferedImage crop(BufferedImage texture) {
            WritableRaster raster = texture.getAlphaRaster();
            if (raster == null)
                return texture;
            int width = raster.getWidth();
            int height = raster.getHeight();
            int left = 0;
            int top = 0;
            int right = width - 1;
            int bottom = height - 1;
            int minRight = width - 1;
            int minBottom = height - 1;

            top:
            for (;top < bottom; top++){
                for (int x = 0; x < width; x++){
                    if (raster.getSample(x, top, 0) != 0){
                        minRight = x;
                        minBottom = top;
                        break top;
                    }
                }
            }

            left:
            for (;left < minRight; left++){
                for (int y = height - 1; y > top; y--){
                    if (raster.getSample(left, y, 0) != 0){
                        minBottom = y;
                        break left;
                    }
                }
            }

            bottom:
            for (;bottom > minBottom; bottom--){
                for (int x = width - 1; x >= left; x--){
                    if (raster.getSample(x, bottom, 0) != 0){
                        minRight = x;
                        break bottom;
                    }
                }
            }

            right:
            for (;right > minRight; right--){
                for (int y = bottom; y >= top; y--){
                    if (raster.getSample(right, y, 0) != 0){
                        break right;
                    }
                }
            }

            return texture.getSubimage(left, top, right - left + 1, bottom - top + 1);
        }
    }
}
