package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.font.FontAsset;
import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.asset.font.FontAssetImpl;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterImpl;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProvider;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProviderImpl;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
import com.ehhthan.scholarapi.location.NamespacedKey;
import com.ehhthan.scholarapi.location.NamespacedKeyFactory;
import com.ehhthan.scholarapi.location.NamespacedKeyImpl;
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

        bind(PackMCMeta.class).toProvider(PackMCMetaProvider.class);

        bind(ResourcePack.class).to(ResourcePackImpl.class);

        install(new FactoryModuleBuilder()
            .implement(NamespacedKey.class, NamespacedKeyImpl.class)
            .build(NamespacedKeyFactory.class));

        install(new FactoryModuleBuilder().build(TextAssetFactory.class));

        install(new FactoryModuleBuilder().build(AssetFileFactory.class));

        install(new FactoryModuleBuilder()
            .implement(FontAsset.class, FontAssetImpl.class)
            .build(FontAssetFactory.class));

        install(new FactoryModuleBuilder()
            .implement(FontCharacter.class, FontCharacterImpl.class)
            .build(FontCharacterFactory.class));

        install(new FactoryModuleBuilder()
            .implement(BitmapFontProvider.class, Names.named("bitmap"), BitmapFontProviderImpl.class)
            .build(FontProviderFactory.class));
    }
}
