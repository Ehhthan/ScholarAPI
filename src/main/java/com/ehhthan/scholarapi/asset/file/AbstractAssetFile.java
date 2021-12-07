package com.ehhthan.scholarapi.asset.file;

import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.google.common.base.Preconditions;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: 12/7/2021 bad abstract?
public abstract class AbstractAssetFile implements AssetFile {
    private static final Pattern NAMESPACED_KEY_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+):(?<key>[a-z0-9/._-]+)");

    private final String minecraftPath;
    private final NamespacedKey namespacedKey;

    private final File file;

    AbstractAssetFile(NamespacedKeyFactory namespacedKeyFactory, File directory, String type, String minecraftPath) {
        this.minecraftPath = minecraftPath;

        Matcher matcher = NAMESPACED_KEY_PATTERN.matcher(minecraftPath);
        if (matcher.find()) {
            String relativePath = matcher.group("namespace") + '/' + type + '/' + matcher.group("key");

            File file = new File(directory, relativePath);

            if (!file.getName().contains("."))
                file = new File(file.getPath() + extension());

            Preconditions.checkArgument(file.exists(), "Specified file does not exist: %s", file.getPath());

            this.file = file;
            this.namespacedKey = namespacedKeyFactory.namespacedKey(matcher.group("namespace"), matcher.group("key").replace(extension(), ""));
        } else {
            throw new IllegalArgumentException("Invalid path specified: " + minecraftPath);
        }
    }

    @Override
    public File asFile() {
        return file;
    }

    @Override
    public String asMinecraftPath() {
        return minecraftPath;
    }

    public NamespacedKey namespacedKey() {
        return namespacedKey;
    }
}
