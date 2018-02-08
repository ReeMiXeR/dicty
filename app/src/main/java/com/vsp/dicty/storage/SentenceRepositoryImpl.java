package com.vsp.dicty.storage;

import android.content.Context;

import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.domain.repository.SentenceRepository;
import com.vsp.dicty.network.RestSentenceClient;
import com.vsp.dicty.network.model.RestSentence;
import com.vsp.dicty.storage.convertors.StorageSentenceConverter;

import io.realm.Realm;

public class SentenceRepositoryImpl implements SentenceRepository, RestSentenceClient.RestSentenceResultCallback {

    private Context mContext;
    private SentenceRepositoryCallback mCallback;
    private Realm mRealm;


    public SentenceRepositoryImpl(Context context) {
        mContext = context;
        Realm.init(mContext);
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public boolean insertSentenceTranslate(StorageSentence model) {
        
        return false;
    }

    @Override
    public boolean updateSentenceTranslate(StorageSentence model) {
        return false;
    }

    @Override
    public void getSentenceTranslate(StorageSentence text, SentenceRepositoryCallback callback) {
        mCallback = callback;
        // TODO: 09.04.2017 check database
        RestSentenceClient.newInstance().translateSentence(text, this);
    }

    @Override
    public void handleRestSentenceSuccess(RestSentence result) {
        StorageSentence sentence = StorageSentenceConverter.convertRestToStorage(result);

        mCallback.handleSentenceSuccess(sentence);
    }

    @Override
    public void handleRestSentenceError(String errorMessage) {
        mCallback.handleSentenceError(errorMessage);
    }

    @Override
    public boolean delete(StorageSentence model) {
        return false;
    }

    //    public void getWordTranslate(UntranslatedText sentence, SentenceRepositoryCallback handler) {
//        mHandler = handler;
//        RestSentenceClient.RestSentenceResultCallback() {
//            @Override
//            public void handleRestSentenceSuccess(StorageSentence result) {
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
        void handleSentenceSuccess(StorageSentence result);
        void handleSentenceError(String errorMessage);
    }
}
