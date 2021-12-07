package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.file.AssetFileImpl;
import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
import com.ehhthan.scholarapi.asset.text.TextAssetFactoryImpl;
import com.ehhthan.scholarapi.location.NamespaceKeyFactoryImpl;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.ehhthan.scholarapi.mcmeta.PackMCMetaProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

import java.io.File;

public class ScholarBinder extends AbstractModule {
    private final File workingDirectory;

    public ScholarBinder(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    protected void configure() {
        bind(File.class).annotatedWith(Names.named("workingDirectory")).toInstance(workingDirectory);

        bind(AssetFile.FileType.class).annotatedWith(Names.named(AssetFile.FileType.TEXTURES.path())).toInstance(AssetFile.FileType.TEXTURES);

        bind(NamespacedKeyFactory.class).to(NamespaceKeyFactoryImpl.class).in(Scopes.SINGLETON);
        bind(PackMCMeta.class).toProvider(PackMCMetaProvider.class);
        bind(TextAssetFactory.class).to(TextAssetFactoryImpl.class).in(Scopes.SINGLETON);
        bind(ResourcePack.class).to(ResourcePackImpl.class);

        install(new FactoryModuleBuilder().implement(AssetFile.class, AssetFileImpl.class).build(AssetFileFactory.class));
    }
}
