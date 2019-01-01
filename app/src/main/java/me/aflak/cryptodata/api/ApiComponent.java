package me.aflak.cryptodata.api;

import javax.inject.Singleton;

import dagger.Component;
import me.aflak.cryptodata.app.AppModule;
import me.aflak.cryptodata.views.CryptoWidget;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(CryptoWidget widget);
}
