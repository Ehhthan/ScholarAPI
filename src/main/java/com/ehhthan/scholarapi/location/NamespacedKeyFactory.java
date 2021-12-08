package com.ehhthan.scholarapi.location;

import com.google.common.base.Preconditions;
import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Arrays;

public interface NamespacedKeyFactory {
    NamespacedKey minecraftPath(@NotNull @Named("minecraftPath") String path);

    NamespacedKey filePath(@NotNull @Named("filePath") Path path);

    class NamespacedKeyFactoryImpl implements NamespacedKeyFactory {
        @Override
        public NamespacedKey minecraftPath(@NotNull @Named("minecraftPath") String path) {
            return new NamespacedKeyImpl(path);
        }

        @Override
        public NamespacedKey filePath(@NotNull @Named("filePath") Path path) {
            String[] split = path.toString().split("\\\\", 3);
            Preconditions.checkArgument(split.length == 3, "Invalid path. Cannot be converted to NamespacedKey: %s", Arrays.toString(split));
            return new NamespacedKeyImpl(split[0] + ":" + split[2]);
        }
    }
}
