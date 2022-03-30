package com.ehhthan.scholarapi.ztest;

import com.ehhthan.scholarapi.asset.AssetLocation;
import com.google.common.base.Preconditions;

import java.io.File;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public interface AssetCollectionFactory {
    AssetCollection create(File directory);
    class AssetCollectionFactoryImpl implements AssetCollectionFactory {
        private static final Pattern NAMESPACE_PATTERN = Pattern.compile("[a-z0-9._-]+");
        private static final Pattern EXTENSION_PATTERN = Pattern.compile("[.][^/.]+$");

        private final Set<String> validLocations = new HashSet<>();

        public AssetCollectionFactoryImpl() {
            for (AssetLocation value : AssetLocation.values()) {
                this.validLocations.add(value.path());
            }
        }

        @Override
        public AssetCollection create(File directory) {
            String namespace = directory.getName();
            Preconditions.checkArgument(directory.exists() && directory.isDirectory() && NAMESPACE_PATTERN.matcher(namespace).matches(),
                "Asset directory is invalid: %s", directory.toPath());

            AssetCollection.Builder builder = AssetCollection.create(namespace);
            File[] locations = directory.listFiles();
            if (locations != null) {
                for (File file : locations) {
                    if (file.isDirectory() && validLocations.contains(file.getName().replaceFirst(EXTENSION_PATTERN.pattern(), ""))) {

                    }
                }
            }
            return builder.build();
        }
    }
}
