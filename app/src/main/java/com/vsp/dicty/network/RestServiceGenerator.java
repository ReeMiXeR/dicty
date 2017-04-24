package com.vsp.dicty.network;

import com.vsp.dicty.network.services.SentenceTranslateService;
import com.vsp.dicty.network.services.WordTranslateService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceGenerator {

    // TODO: 30.03.2017 transfer api_key to safe place + should use URL coding
    private static final String SENTENCE_TRANSLATE_URL = "https://translate.yandex.net/";
    private static final String WORD_TRANSLATE_URL = "https://dictionary.yandex.net/";
    public static final String SENTENCE_API_KEY = "trnsl.1.1.20170315T173715Z.f42b562752b99b77.15bf75cd96891dad2ea074b5f011dcac24c0c971";
    public static final String WORD_API_KEY = "dict.1.1.20170409T163755Z.f9ab3662984ddd2b.0a2d6e72083b638111a11e5720df2b5461da288c";

    // TODO: 30.03.2017 http logging

    private static <T> T getService(Class<T> serviceClass, String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public static SentenceTranslateService getSentenceService() {
        return getService(SentenceTranslateService.class, SENTENCE_TRANSLATE_URL);
    }

    public static WordTranslateService getWordService() {
        return getService(WordTranslateService.class, WORD_TRANSLATE_URL);
    }
}
