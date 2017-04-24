package com.vsp.dicty.domain.interactors.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.CurrentLangInteractor;
import com.vsp.dicty.domain.interactors.base.AbstractInteractor;
import com.vsp.dicty.domain.repository.LanguageRepository;
import com.vsp.dicty.storage.LanguageRepositoryImpl;
import com.vsp.dicty.storage.model.LangDir;


public class CurrentLangInteractorImpl extends AbstractInteractor implements CurrentLangInteractor {

    private Context mContext;
    private CurrentLangInteractor.Callback mCallback;

    public CurrentLangInteractorImpl(Executor threadExecutor,
                                     MainThread mainThread,
                                     CurrentLangInteractor.Callback callback,
                                     Context context) {
        super(threadExecutor, mainThread);
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void run() {
        LanguageRepository languageRepository = new LanguageRepositoryImpl(mContext);
        final LangDir currLang = languageRepository.getCurrentLanguage();

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onCurrLangReceived(currLang);
            }
        });
        this.onFinished();
    }

}
