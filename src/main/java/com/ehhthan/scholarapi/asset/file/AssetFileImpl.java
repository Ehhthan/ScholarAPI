package com.ehhthan.scholarapi.asset.file;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssetFileImpl implements AssetFile {
    private static final Pattern NAMESPACED_KEY_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+):(?<key>[a-z0-9/._-]+)");

    private final FileType type;
    private final String minecraftPath;

    private final File file;

    @Inject
    AssetFileImpl(@Named("workingDirectory") File directory, @Assisted FileType type, @Assisted String minecraftPath) {
        this.minecraftPath = minecraftPath;
        this.type = type;

        Matcher matcher = NAMESPACED_KEY_PATTERN.matcher(minecraftPath);
        if (matcher.find()) {
            String relativePath = matcher.group("namespace") + '/' + type.path() + '/' + matcher.group("key");
            this.file = new File(directory, relativePath);
            Preconditions.checkArgument(file.exists(), "Specified file does not exist: %s", file.getPath());
        } else {
            throw new IllegalArgumentException("Invalid path specified: " + minecraftPath);
        }
    }

    public FileType type() {
        return type;
    }

    @Override
    public File asFile() {
        return file;
    }

    @Override
    public String asMinecraftPath() {
        return minecraftPath;
    }
}
