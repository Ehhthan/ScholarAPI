package com.ehhthan.scholarapi.asset.file;

import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;
import java.nio.file.Path;

public interface AssetFileFactory {
    AssetFile texture(NamespacedKey namespacedKey);

    AssetFile model(NamespacedKey namespacedKey);

    AssetFile font(NamespacedKey namespacedKey);

    AssetFile texture(File file);

    AssetFile model(File file);

    AssetFile font(File file);

    AssetFile asset(AssetFile.Type type, NamespacedKey namespacedKey);

    AssetFile asset(AssetFile.Type type, File file);

    class AssetFileFactoryImpl implements AssetFileFactory {
        private final File directory;
        private final NamespacedKeyFactory namespacedKeyFactory;

        @Inject
        AssetFileFactoryImpl(@Named("workingDirectory") File directory, NamespacedKeyFactory namespacedKeyFactory) {
            this.directory = directory;
            this.namespacedKeyFactory = namespacedKeyFactory;
        }

        @Override
        public AssetFile texture(NamespacedKey namespacedKey) {
            return asset(AssetFile.Type.TEXTURE, namespacedKey);
        }

        @Override
        public AssetFile model(NamespacedKey namespacedKey) {
            return asset(AssetFile.Type.MODEL, namespacedKey);
        }

        @Override
        public AssetFile font(NamespacedKey namespacedKey) {
            return asset(AssetFile.Type.FONT, namespacedKey);
        }

        @Override
        public AssetFile texture(File file) {
            return asset(AssetFile.Type.TEXTURE, file);
        }

        @Override
        public AssetFile model(File file) {
            return asset(AssetFile.Type.MODEL, file);
        }

        @Override
        public AssetFile font(File file) {
            return asset(AssetFile.Type.FONT, file);
        }


        @Override
        public AssetFile asset(AssetFile.Type type, File file) {
            NamespacedKey namespacedKey = namespacedKeyFactory.filePath(directory.toPath().relativize(file.toPath()));
            return asset(type, namespacedKey);
        }

        @Override
        public AssetFile asset(AssetFile.Type type, NamespacedKey namespacedKey) {
            File file = new File(directory, namespacedKey.namespace() + '/' + type.path() + '/' + namespacedKey.key());

            if (!file.getName().contains("."))
                file = new File(file.getPath() + type.extension());

            Preconditions.checkArgument(file.exists(), "Specified file does not exist: %s", file.getPath());

            return new AssetFileImpl(file, type, namespacedKey);
        }

    }
}
