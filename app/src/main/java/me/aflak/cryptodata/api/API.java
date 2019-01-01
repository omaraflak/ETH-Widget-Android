package me.aflak.cryptodata.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "https://pro-api.coinmarketcap.com";

    @GET("v1/cryptocurrency/quotes/latest")
    Call<ResponseBody> getCryptoData(@Header("X-CMC_PRO_API_KEY") String authorization, @Query("id") int cryptoId, @Query("convert") String currency);
}
