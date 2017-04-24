package com.vsp.dicty.storage;

import android.content.Context;

import com.vsp.dicty.domain.model.UntranslatedText;
import com.vsp.dicty.domain.repository.WordRepository;
import com.vsp.dicty.network.RestWordClient;
import com.vsp.dicty.network.model.RestWord;
import com.vsp.dicty.storage.convertors.StorageWordConverter;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;

import io.realm.RealmList;
import timber.log.Timber;

public class WordRepositoryImpl implements WordRepository, RestWordClient.RestWordResultCallback {

    private Context mContext;
    private WordRepositoryCallback mCallback;
    private UntranslatedText text;

    public WordRepositoryImpl(Context context) {
        mContext = context;
    }

    @Override
    public boolean insertWordTranslate() {
        return false;
    }

    @Override
    public boolean updateWordTranslate() {
        return false;
    }

    @Override
    public ArrayList<StorageWord> getWordTranslate(UntranslatedText word, WordRepositoryCallback callback) {
        mCallback = callback;
        text = word;
        return RestWordClient.newInstance().translateWord(word, this);
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public void handleRestWordError(String errorMessage) {
        mCallback.handleWordError(errorMessage);
    }

    public interface WordRepositoryCallback {
        void handleWordError(String errorMessage);
    }
}
