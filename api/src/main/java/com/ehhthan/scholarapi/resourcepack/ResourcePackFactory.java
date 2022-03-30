package com.ehhthan.scholarapi.resourcepack;

import java.io.File;

public interface ResourcePackFactory {
    ResourcePack create(File directory);

    class ResourcePackFactoryImpl implements ResourcePackFactory {

        @Override
        public ResourcePack create(File directory) {
            return null;
        }
    }
}
