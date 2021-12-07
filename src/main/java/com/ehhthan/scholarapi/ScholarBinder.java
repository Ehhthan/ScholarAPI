package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.file.AssetFile;
import com.ehhthan.scholarapi.asset.file.AssetFileFactory;
import com.ehhthan.scholarapi.asset.file.ModelAssetFile;
import com.ehhthan.scholarapi.asset.file.TextureAssetFile;
import com.ehhthan.scholarapi.asset.font.character.FontCharacter;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterImpl;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProvider;
import com.ehhthan.scholarapi.asset.font.provider.BitmapFontProviderImpl;
import com.ehhthan.scholarapi.asset.font.provider.FontProvider;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.asset.text.JsonTextAsset;
import com.ehhthan.scholarapi.asset.text.StringTextAsset;
import com.ehhthan.scholarapi.asset.text.TextAsset;
import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
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

        bind(NamespacedKeyFactory.class).to(NamespaceKeyFactoryImpl.class).in(Scopes.SINGLETON);
        bind(PackMCMeta.class).toProvider(PackMCMetaProvider.class);

        bind(ResourcePack.class).to(ResourcePackImpl.class);

        install(new FactoryModuleBuilder().build(TextAssetFactory.class));

        install(new FactoryModuleBuilder().build(AssetFileFactory.class));

        install(new FactoryModuleBuilder()
            .implement(FontCharacter.class, FontCharacterImpl.class)
            .build(FontCharacterFactory.class));

        install(new FactoryModuleBuilder()
            .implement(BitmapFontProvider.class, Names.named("bitmap"), BitmapFontProviderImpl.class)
            .build(FontProviderFactory.class));
    }
}
