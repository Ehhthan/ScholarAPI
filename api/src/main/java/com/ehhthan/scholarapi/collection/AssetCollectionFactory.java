package com.ehhthan.scholarapi.collection;

import com.ehhthan.scholarapi.asset.AssetLocation;
import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public interface AssetCollectionFactory {
    Map<String, AssetCollection> create(File directory);

    class AssetCollectionFactoryImpl implements AssetCollectionFactory {
        private static final Pattern NAMESPACE_PATTERN = Pattern.compile("[a-z0-9._-]+");
        private static final Pattern EXTENSION_PATTERN = Pattern.compile("[.][^/.]+$");

        public AssetCollectionFactoryImpl() {
        }

        @Override
        public Map<String, AssetCollection> create(@NotNull File assets) {
            Preconditions.checkArgument(assets.exists() && assets.isDirectory() && assets.getName().equals("assets"),
                "Not a valid assets directory: %s", assets.toPath());

            Map<String, AssetCollection> collections = new HashMap<>();

            File[] locations = assets.listFiles();
            if (locations != null) {
                for (File directory : locations) {
                    Preconditions.checkArgument(directory.exists() && directory.isDirectory(),
                        "Asset directory is invalid: %s", directory.toPath());

                    String namespace = directory.getName();
                    Preconditions.checkArgument(NAMESPACE_PATTERN.matcher(namespace).matches(), "Invalid namespace format: %s", namespace);

                    AssetLocation.isPath("null");
                }
            }

            return collections;
        }
    }
}
