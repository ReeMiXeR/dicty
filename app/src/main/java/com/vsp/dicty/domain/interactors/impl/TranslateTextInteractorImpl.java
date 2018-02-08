package com.vsp.dicty.domain.interactors.impl;

import android.content.Context;

import com.pixplicity.easyprefs.library.Prefs;
import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.TranslateTextInteractor;
import com.vsp.dicty.domain.interactors.base.AbstractInteractor;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.domain.repository.SentenceRepository;
import com.vsp.dicty.domain.repository.WordRepository;
import com.vsp.dicty.storage.SentenceRepositoryImpl;
import com.vsp.dicty.storage.WordRepositoryImpl;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;

import static com.vsp.dicty.storage.LanguageRepositoryImpl.CURRENT_LANG_DIR;
import static com.vsp.dicty.storage.LanguageRepositoryImpl.DEFAULT_LANG_DIR;

public class TranslateTextInteractorImpl extends AbstractInteractor implements TranslateTextInteractor,
        SentenceRepositoryImpl.SentenceRepositoryCallback, WordRepositoryImpl.WordRepositoryCallback {

    private TranslateCallback mTranslateCallback;
    private Context mContext;
    private StorageSentence mUntranslatedText;
    private SentenceRepository mSentenceRepository;

    public TranslateTextInteractorImpl(Executor threadExecutor,
                                       MainThread mainThread,
                                       TranslateCallback translateCallback,
                                       Context context,
                                       StorageSentence text) {
        super(threadExecutor, mainThread);
        mTranslateCallback = translateCallback;
        mContext = context;
        mUntranslatedText = text;
    }

    public static boolean isWord(String text) {
        return (text.length() > 0 && text.split("\\s+").length == 1);
    }

    @Override
    public void run() {

        mUntranslatedText.setFromLang(Prefs.getString(CURRENT_LANG_DIR, DEFAULT_LANG_DIR));

        WordRepository wordRepository = new WordRepositoryImpl(mContext);
        mSentenceRepository = new SentenceRepositoryImpl(mContext);

        if (isWord(mUntranslatedText.getNotTranslatedText())) {
            ArrayList<StorageWord> wordArray = wordRepository.getWordTranslate(mUntranslatedText, this);
            if (wordArray == null)
                mSentenceRepository.getSentenceTranslate(mUntranslatedText, this);
            if (wordArray != null && wordArray.size() == 0)
                mSentenceRepository.getSentenceTranslate(mUntranslatedText, this);
            else
                postWordToMainThread(wordArray);
        } else {
            mSentenceRepository.getSentenceTranslate(mUntranslatedText, this);
        }
    }

    private void postWordToMainThread(final ArrayList<StorageWord> result) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mTranslateCallback.onWordTranslated(result);
            }
        });
    }

    @Override
    public void handleWordError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mTranslateCallback.onTranslateError(errorMessage);
            }
        });
    }

    @Override
    public void handleSentenceSuccess(final StorageSentence result) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mTranslateCallback.onSentenceTranslated(result);
            }
        });
    }

    @Override
    public void handleSentenceError(final String errorMessage) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mTranslateCallback.onTranslateError(errorMessage);
            }
        });
    }

}
