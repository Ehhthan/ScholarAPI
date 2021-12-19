package com.ehhthan.scholarapi.location;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface NamespacedKeyFactory {
    NamespacedKey minecraftPath(@NotNull String path);

    NamespacedKey filePath(File file);

    NamespacedKey filePath(@NotNull Path path);

    class NamespacedKeyFactoryImpl implements NamespacedKeyFactory {
        private static final Pattern MINECRAFT_PATH_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+):(?<key>[a-z0-9/._-]+)");

        private final File directory;

        @Inject
        public NamespacedKeyFactoryImpl(@Named("workingDirectory") File directory) {
            this.directory = directory;
        }

        @Override
        public NamespacedKey minecraftPath(@NotNull String path) {
            Preconditions.checkArgument(path.length() < 256, "NamespacedKey length must be less than 256 characters: %s", path);

            String namespace, key;

            Matcher pathMatcher = MINECRAFT_PATH_PATTERN.matcher(path);
            if (pathMatcher.find()) {
                namespace = pathMatcher.group("namespace");
                key = pathMatcher.group("key");
            } else {
                throw new IllegalArgumentException("Invalid path specified: " + path);
            }

            return new NamespacedKeyImpl(namespace, key);
        }

        @Override
        public NamespacedKey filePath(File file) {
            return filePath(file.toPath());
        }

        @Override
        public NamespacedKey filePath(@NotNull Path path) {
            String stringPath = directory.toPath().relativize(path).toString();
            String[] split = stringPath.split("\\\\", 3);
            Preconditions.checkArgument(split.length == 3, "Invalid path. Cannot be converted to NamespacedKey: %s", Arrays.toString(split));

            return new NamespacedKeyImpl(split[0], split[2]);
        }
    }
}
