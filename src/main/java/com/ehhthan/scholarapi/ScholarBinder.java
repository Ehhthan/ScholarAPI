package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.asset.font.FontAssetImpl;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProvider;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProviderImpl;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.ehhthan.scholarapi.mcmeta.PackMCMeta;
import com.ehhthan.scholarapi.mcmeta.PackMCMetaProvider;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.ehhthan.scholarapi.resourcepack.ResourcePackImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.io.File;

public class ScholarBinder extends AbstractModule {
    private final File workingDirectory;

    public ScholarBinder(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    @Override
    protected void configure() {
        bind(PackMCMeta.class).toProvider(PackMCMetaProvider.class);

        bind(ResourcePack.class).to(ResourcePackImpl.class);

        bind(NamespacedKeyFactory.class).to(NamespacedKeyFactory.NamespacedKeyFactoryImpl.class);

        bind(TextAssetFactory.class).to(TextAssetFactory.TextAssetFactoryImpl.class);

        bind(AssetFileFactory.class).to(AssetFileFactory.AssetFileFactoryImpl.class);

        bind(FontCharacterFactory.class).to(FontCharacterFactory.FontCharacterFactoryImpl.class);

        install(new FactoryModuleBuilder()
            .implement(FontAsset.class, FontAssetImpl.class)
            .build(FontAssetFactory.class));

        install(new FactoryModuleBuilder()
            .implement(BitmapFontProvider.class, Names.named("bitmap"), BitmapFontProviderImpl.class)
            .build(FontProviderFactory.class));
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Named("workingDirectory") File provideWorkingDirectory() {
        return workingDirectory;
    }
}
