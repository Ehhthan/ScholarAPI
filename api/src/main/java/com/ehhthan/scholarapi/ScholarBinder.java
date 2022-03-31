package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.asset.font.FontAssetFactory;
import com.ehhthan.scholarapi.asset.font.character.FontCharacterFactory;
import com.ehhthan.scholarapi.asset.font.provider.FontProviderFactory;
import com.ehhthan.scholarapi.asset.text.TextAssetFactory;
import com.ehhthan.scholarapi.asset.texture.TextureAssetFactory;
import com.ehhthan.scholarapi.mcmeta.McMetaFactory;
import com.ehhthan.scholarapi.namespacedkey.NamespacedKeyFactory;
import com.ehhthan.scholarapi.resourcepack.ResourcePackFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

// TODO: 12/10/2021 separate into different modules
public class ScholarBinder extends AbstractModule {
    @Override
    protected void configure() {
        bind(ResourcePackFactory.class).to(ResourcePackFactory.ResourcePackFactoryImpl.class);

        bind(NamespacedKeyFactory.class).to(NamespacedKeyFactory.NamespacedKeyFactoryImpl.class);

        bind(TextAssetFactory.class).to(TextAssetFactory.TextAssetFactoryImpl.class);

        bind(FontProviderFactory.class).to(FontProviderFactory.FontProviderFactoryImpl.class);

        bind(TextureAssetFactory.class).to(TextureAssetFactory.TextureAssetFactoryImpl.class);

        bind(FontAssetFactory.class).to(FontAssetFactory.FontAssetFactoryImpl.class);

        bind(FontCharacterFactory.class).to(FontCharacterFactory.FontCharacterFactoryImpl.class);

        bind(McMetaFactory.class).to(McMetaFactory.McMetaFactoryImpl.class);
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }
}
