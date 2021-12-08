package com.ehhthan.scholarapi.asset.file;

import com.ehhthan.scholarapi.location.NamespacedKey;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.File;

public interface AssetFileFactory {
    @Named("texture") AssetFile texture(NamespacedKey namespacedKey);

    @Named("model") AssetFile model(NamespacedKey namespacedKey);

    @Named("font") AssetFile font(NamespacedKey namespacedKey);

    @Named("asset") AssetFile asset(AssetFile.Type type, NamespacedKey namespacedKey);

    class AssetFileFactoryImpl implements AssetFileFactory {
        private final File directory;

        @Inject
        AssetFileFactoryImpl(@Named("workingDirectory") File directory) {
            this.directory = directory;
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
        public AssetFile asset(AssetFile.Type type, NamespacedKey namespacedKey) {
            File file = new File(directory, namespacedKey.namespace() + '/' + type.path() + '/' + namespacedKey.key());

            if (!file.getName().contains("."))
                file = new File(file.getPath() + type.extension());

            Preconditions.checkArgument(file.exists(), "Specified file does not exist: %s", file.getPath());

            return new AssetFileImpl(file, type, namespacedKey);
        }
    }
}
