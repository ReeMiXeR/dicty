package com.vsp.dicty.presentation.presenters.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.TranslateTextInteractor;
import com.vsp.dicty.domain.interactors.impl.SaveSentenceInteractorImpl;
import com.vsp.dicty.domain.interactors.impl.TranslateTextInteractorImpl;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.presentation.presenters.TranslatePresenter;
import com.vsp.dicty.presentation.presenters.base.AbstractPresenter;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;

public class TranslatePresenterImpl extends AbstractPresenter implements TranslatePresenter, TranslateTextInteractor.TranslateCallback{

    private TranslatePresenter.View mView;
    private Context mContext;

    public TranslatePresenterImpl(Executor executor, MainThread mainThread,
                                  View view, Context context) {
        super(executor, mainThread);
        mView = view;
        mContext = context;
    }

    @Override
    public void create() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void saveSentence(StorageSentence storageSentence) {
        SaveSentenceInteractorImpl saveSentenceInteractor = new SaveSentenceInteractorImpl(
                mExecutor,
                mMainThread,
                mContext,
                storageSentence
        );
    }

    @Override
    public void translateText(StorageSentence text) {
        TranslateTextInteractor translateTextInteractor = new TranslateTextInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mContext,
                text
        );
        translateTextInteractor.execute();
    }

    @Override
    public void onSentenceTranslated(StorageSentence storageSentence) {
        mView.onSentenceTranslated(storageSentence);
    }

    @Override
    public void onWordTranslated(ArrayList<StorageWord> word) {
        mView.onWordTranslated(word);
    }

    @Override
    public void onTranslateError(String error) {
        mView.onTranslateError(error);
    }
}
