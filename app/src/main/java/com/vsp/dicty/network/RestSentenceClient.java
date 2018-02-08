package com.vsp.dicty.network;

import com.vsp.dicty.network.model.RestLangs;
import com.vsp.dicty.network.model.RestSentence;
import com.vsp.dicty.network.services.SentenceTranslateService;
import com.vsp.dicty.storage.model.StorageSentence;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vsp.dicty.network.RestServiceGenerator.SENTENCE_API_KEY;

public class RestSentenceClient implements Callback<RestSentence> {

    private RestSentenceResultCallback mSentenceCallback;
    private RestLanguageResultCallback mLanguageCallback;
    private SentenceTranslateService mSentenceService;
    private static final RestSentenceClient sInstance = new RestSentenceClient();

    private RestSentenceClient() {
        mSentenceService = RestServiceGenerator.getSentenceService();
    }

    public static RestSentenceClient newInstance() {
        return sInstance;
    }

    public void translateSentence(StorageSentence text, RestSentenceResultCallback callback) {
        mSentenceCallback = callback;

        Call<RestSentence> callSentence = mSentenceService.getSentenceTranslate(
                SENTENCE_API_KEY,
                text.getNotTranslatedText(),
                text.getFromLang());
        callSentence.enqueue(this);
    }

    @Override
    public void onResponse(Call<RestSentence> call, Response<RestSentence> response) {
        if (response.isSuccessful()) {
            mSentenceCallback.handleRestSentenceSuccess(response.body());
        } else
            mSentenceCallback.handleRestSentenceError("Error");
    }

    @Override
    public void onFailure(Call<RestSentence> call, Throwable t) {
        mSentenceCallback.handleRestSentenceError("Error");
    }

    public void getLanguages(RestLanguageResultCallback callback) {
        mLanguageCallback = callback;

        Call<RestLangs> callSentence = mSentenceService.getAvailableLangs(
                "ru"
        );

    }

    public interface RestSentenceResultCallback {
        void handleRestSentenceSuccess(RestSentence result);
        void handleRestSentenceError(String errorMessage);
    }

    public interface RestLanguageResultCallback {
        void handleRestSentenceSuccess(RestSentence result);
        void handleRestSentenceError(String errorMessage);
    }
}
