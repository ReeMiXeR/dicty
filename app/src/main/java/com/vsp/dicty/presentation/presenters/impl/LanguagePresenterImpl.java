package com.vsp.dicty.presentation.presenters.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.CurrentLangInteractor;
import com.vsp.dicty.domain.interactors.LangListInteractor;
import com.vsp.dicty.domain.interactors.UpdateLangInteractor;
import com.vsp.dicty.domain.interactors.impl.CurrentLangInteractorImpl;
import com.vsp.dicty.domain.interactors.impl.LangListInteractorImpl;
import com.vsp.dicty.domain.interactors.impl.UpdateLangInteractorImpl;
import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.presentation.presenters.LanguagePresenter;
import com.vsp.dicty.presentation.presenters.base.AbstractPresenter;
import com.vsp.dicty.storage.model.LangDir;

import java.util.ArrayList;
import java.util.HashMap;

public class LanguagePresenterImpl extends AbstractPresenter implements LanguagePresenter,
        LangListInteractor.Callback, CurrentLangInteractor.Callback, UpdateLangInteractor.Callback {

    private TranslateFragView mTranslateFragView;
    private LangsPickerDialogView mLangsPickerDialogView;
    private Context mContext;

    public LanguagePresenterImpl(Executor executor, MainThread mainThread,
                                 TranslateFragView translateFragView, Context context) {
        super(executor, mainThread);
        mTranslateFragView = translateFragView;
        mContext = context;
    }

    public LanguagePresenterImpl(Executor executor, MainThread mainThread,
                                 LangsPickerDialogView langsPickerDialogView, Context context) {
        super(executor, mainThread);
        mLangsPickerDialogView = langsPickerDialogView;
        mContext = context;
    }

    @Override
    public void create() {
        getCurrentLang();
    }

    @Override
    public void resume() {
        getCurrentLang();
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
    public void onLanguageError(String error) {

    }

    @Override
    public void updateCurrentLang(String langCode, String langDir) {
        UpdateLangInteractorImpl languageInteractor = new UpdateLangInteractorImpl(
                mExecutor,
                mMainThread,
                mContext,
                langCode,
                langDir
        );
        languageInteractor.execute();
    }

    @Override
    public void onLangUpdated() {
        getCurrentLang();
    }

    @Override
    public void onLangListReceived(ArrayList<Lang> result) {
        mLangsPickerDialogView.onLangListReceived(result);
    }

    @Override
    public void onCurrLangReceived(LangDir result) {
        mTranslateFragView.onLangDirReceived(result);
    }

    @Override
    public void getCurrentLang() {
        CurrentLangInteractor languageInteractor = new CurrentLangInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mContext
        );
        languageInteractor.execute();
    }

    @Override
    public void getLangList() {
        LangListInteractor languageInteractor = new LangListInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mContext
        );
        languageInteractor.execute();
    }



    @Override
    public void onError(String message) {

    }
}
