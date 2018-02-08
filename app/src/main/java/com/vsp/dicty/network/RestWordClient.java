package com.vsp.dicty.network;

import com.vsp.dicty.network.model.RestWord;
import com.vsp.dicty.network.services.WordTranslateService;
import com.vsp.dicty.storage.convertors.StorageWordConverter;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.storage.model.StorageWord;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

import static com.vsp.dicty.network.RestServiceGenerator.WORD_API_KEY;

public class RestWordClient {

    private RestWordResultCallback mCallback;
    private WordTranslateService mWordService;
    private Call<RestWord> callWord;
    private static final RestWordClient sInstance = new RestWordClient();

    public static RestWordClient newInstance() {
        return sInstance;
    }

    private RestWordClient() {
        mWordService = RestServiceGenerator.getWordService();
    }

    public ArrayList<StorageWord> translateWord(StorageSentence text, RestWordResultCallback callback) {
        mCallback = callback;

        callWord = mWordService.getWordTranslate(
                WORD_API_KEY,
                text.getFromLang(),
                text.getNotTranslatedText());

        Response<RestWord> response = null;
        try {
            response = callWord.execute();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (response != null && response.isSuccessful() && response.body() != null)
                    return StorageWordConverter.convertRestToStorage(response.body());

        mCallback.handleRestWordError(response.errorBody().toString());
        return null;
    }

    public interface RestWordResultCallback {
        void handleRestWordError(String errorMessage);
    }
}
