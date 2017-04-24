package com.vsp.dicty.storage;

import android.content.Context;

import com.vsp.dicty.domain.model.Sentence;
import com.vsp.dicty.domain.model.UntranslatedText;
import com.vsp.dicty.domain.repository.SentenceRepository;
import com.vsp.dicty.network.RestSentenceClient;
import com.vsp.dicty.network.model.RestSentence;
import com.vsp.dicty.storage.convertors.StorageSentenceConverter;

public class SentenceRepositoryImpl implements SentenceRepository, RestSentenceClient.RestSentenceResultCallback {

    private Context mContext;
    private SentenceRepositoryCallback mCallback;


    public SentenceRepositoryImpl(Context context) {
        mContext = context;
    }

    @Override
    public boolean insertSentenceTranslate(Sentence model) {
        
        return false;
    }

    @Override
    public boolean updateSentenceTranslate(Sentence model) {
        return false;
    }

    @Override
    public void getSentenceTranslate(UntranslatedText text, SentenceRepositoryCallback callback) {
        mCallback = callback;
        // TODO: 09.04.2017 check database
        RestSentenceClient.newInstance().translateSentence(text, this);
    }

    @Override
    public void handleRestSentenceSuccess(RestSentence result) {
        mCallback.handleSentenceSuccess(StorageSentenceConverter.convertRestToStorage(result));
    }

    @Override
    public void handleRestSentenceError(String errorMessage) {
        mCallback.handleSentenceError(errorMessage);
    }

    @Override
    public boolean delete(Sentence model) {
        return false;
    }

    //    public void getWordTranslate(UntranslatedText sentence, SentenceRepositoryCallback handler) {
//        mHandler = handler;
//        RestSentenceClient.RestSentenceResultCallback() {
//            @Override
//            public void handleRestSentenceSuccess(Sentence result) {
//                if (result != null)
//                    handler.handleRestSentenceSuccess(result);
//            }
//        }, new RestSentenceClient.ErrorHandler() {
//            @Override
//            public void handleError(String errorMessage) {
//                handler.handleRestSentenceError(errorMessage);
//            }
//        }
//        );

    public interface SentenceRepositoryCallback {
        void handleSentenceSuccess(Sentence result);
        void handleSentenceError(String errorMessage);
    }
}
