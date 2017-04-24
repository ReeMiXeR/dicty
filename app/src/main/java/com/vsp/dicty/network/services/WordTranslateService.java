package com.vsp.dicty.network.services;

import com.vsp.dicty.network.model.RestWord;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WordTranslateService {

    @GET("/api/v1/dicservice.json/lookup?")
    Call<RestWord> getWordTranslate(@Query("key") String apiKey, @Query("lang") String lang, @Query("text") String word);

}
