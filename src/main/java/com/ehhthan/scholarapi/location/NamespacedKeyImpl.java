package com.ehhthan.scholarapi.location;

import com.google.common.base.Preconditions;
import com.google.inject.name.Named;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class NamespacedKeyImpl implements NamespacedKey {
    private static final Pattern NAMESPACED_KEY_PATTERN = Pattern.compile("(?<namespace>[a-z0-9._-]+):(?<key>[a-z0-9/._-]+)");

    private final String path;
    private final String namespace;
    private final String key;

    private final String withoutExtension;

    NamespacedKeyImpl(@NotNull @Named("path") String path) {
        this.path = path;
        Preconditions.checkArgument(path.length() < 256, "NamespacedKey length must be less than 256 characters: %s", path);

        Matcher matcher = NAMESPACED_KEY_PATTERN.matcher(path);
        if (matcher.find()) {
            this.namespace = matcher.group("namespace");
            this.key = matcher.group("key");
        } else {
            throw new IllegalArgumentException("Invalid path specified: " + path);
        }

        String fileName = path.substring(path.lastIndexOf('/') + 1);

        int i = fileName.lastIndexOf('.');
        this.withoutExtension = (i == -1) ? path : path.replace(fileName, fileName.substring(0, i));
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public String namespace() {
        return namespace;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public String toString() {
        return withoutExtension;
    }
}
