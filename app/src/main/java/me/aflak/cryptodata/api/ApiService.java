package me.aflak.cryptodata.api;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.aflak.cryptodata.entities.Crypto;
import me.aflak.cryptodata.entities.Currency;
import me.aflak.cryptodata.utils.OnFailureListener;
import me.aflak.cryptodata.utils.OnSuccessListener;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {
    private API api;
    private String apiKey;

    public ApiService(String apiKey, API api) {
        this.api = api;
        this.apiKey = apiKey;
    }

    public void getEthereum(Currency currency, OnSuccessListener<Crypto> onSuccessListener, OnFailureListener<String> onFailureListener){
        getCrypto(1027, currency, onSuccessListener, onFailureListener);
    }

    public void getBitcoin(Currency currency, OnSuccessListener<Crypto> onSuccessListener, OnFailureListener<String> onFailureListener){
        getCrypto(1, currency, onSuccessListener, onFailureListener);
    }

    public void getCrypto(final int cryptoId, final Currency currency, final OnSuccessListener<Crypto> onSuccessListener, final OnFailureListener<String> onFailureListener){
        api.getCryptoData(apiKey, cryptoId, currency.getValue()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    if (response.body() != null) {
                        JSONObject object = new JSONObject(response.body().string());
                        JSONObject eth = object.getJSONObject("data").getJSONObject(String.valueOf(cryptoId));
                        JSONObject quote = eth.getJSONObject("quote").getJSONObject(currency.getValue());
                        Crypto crypto = new Crypto.Builder()
                                .setName(eth.getString("name"))
                                .setSymbol(eth.getString("symbol"))
                                .setCurrency(currency.getValue())
                                .setPrice(quote.getDouble("price"))
                                .setPercentChangeDay(quote.getDouble("percent_change_24h"))
                                .setPercentChangeWeek(quote.getDouble("percent_change_7d"))
                                .build();

                        onSuccessListener.onSuccess(crypto);
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                onFailureListener.onFailure(t.getLocalizedMessage());
            }
        });
    }
}
