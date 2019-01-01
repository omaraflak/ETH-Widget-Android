package me.aflak.cryptodata.api;

import android.content.res.Resources;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aflak.cryptodata.R;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    @Provides @Singleton
    public Gson provideGson(){
        return new Gson();
    }

    @Provides @Singleton
    public GsonConverterFactory provideGsonConverter(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides @Singleton
    public API provideAPI(GsonConverterFactory converter){
        return new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(converter)
                .build().create(API.class);
    }

    @Provides @Singleton
    public ApiService provideCMCService(Resources res, API api){
        String apiKey = res.getString(R.string.coin_market_cap_api_key);
        return new ApiService(apiKey, api);
    }
}
