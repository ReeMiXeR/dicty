package com.vsp.dicty.network.services;

import com.vsp.dicty.network.model.RestLangs;
import com.vsp.dicty.network.model.RestSentence;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SentenceTranslateService {

    // TODO: 30.03.2017 callback
    @GET("/api/v1.5/tr.json/translate?")
    Call<RestSentence> getSentenceTranslate(@Query("key") String apiKey, @Query("text") String text, @Query("lang") String lang);

    @GET("/getLangs?")
    Call<RestLangs> getAvailableLangs(@Query("ui") String lang);
}

