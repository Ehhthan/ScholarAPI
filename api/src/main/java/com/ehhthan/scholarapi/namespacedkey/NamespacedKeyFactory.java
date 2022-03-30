package com.ehhthan.scholarapi.namespacedkey;

import com.ehhthan.scholarapi.file.ResourcesDirectory;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface NamespacedKeyFactory {
    NamespacedKey minecraftPath(@NotNull String path);

    NamespacedKey filePath(@NotNull Path path);

    class NamespacedKeyFactoryImpl implements NamespacedKeyFactory {
        private static final Pattern MINECRAFT_PATH_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+):(?<key>[a-z0-9/._-]+)");

        private final File directory;

        @Inject
        public NamespacedKeyFactoryImpl(@ResourcesDirectory File directory) {
            this.directory = directory;
        }

        @Override
        public NamespacedKey minecraftPath(@NotNull String path) {
            String namespace, key;

            Matcher pathMatcher = MINECRAFT_PATH_PATTERN.matcher(path);
            if (pathMatcher.find()) {
                namespace = pathMatcher.group("namespace");
                key = pathMatcher.group("key");
            } else {
                throw new IllegalArgumentException("Invalid path specified: " + path);
            }

            return new NamespacedKey.NamespacedKeyImpl(namespace, key);
        }

        @Override
        public NamespacedKey filePath(@NotNull Path path) {
            String stringPath = directory.toPath().relativize(path).toString();
            // TODO: 12/19/2021 this looks bad
            String[] split = stringPath.split("\\\\", 3);
            Preconditions.checkArgument(split.length == 3, "Invalid path. Cannot be converted to NamespacedKey: %s", Arrays.toString(split));

            return new NamespacedKey.NamespacedKeyImpl(split[0], split[2]);
        }
    }
}
