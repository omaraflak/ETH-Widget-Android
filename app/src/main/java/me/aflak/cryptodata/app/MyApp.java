package me.aflak.cryptodata.app;

import android.app.Application;

import me.aflak.cryptodata.api.ApiComponent;
import me.aflak.cryptodata.api.ApiModule;
import me.aflak.cryptodata.api.DaggerApiComponent;

public class MyApp extends Application {
    public static MyApp app;
    private AppModule appModule;
    private ApiModule apiModule;
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appModule = new AppModule(this);
        apiModule = new ApiModule();
        apiComponent = DaggerApiComponent.builder()
                .appModule(appModule)
                .apiModule(apiModule)
                .build();
    }

    public AppModule getAppModule() {
        return appModule;
    }

    public ApiModule getApiModule() {
        return apiModule;
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
