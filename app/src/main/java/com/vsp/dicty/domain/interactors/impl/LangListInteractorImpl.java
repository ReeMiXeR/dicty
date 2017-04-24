package com.vsp.dicty.domain.interactors.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.LangListInteractor;
import com.vsp.dicty.domain.interactors.base.AbstractInteractor;
import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.domain.repository.LanguageRepository;
import com.vsp.dicty.storage.LanguageRepositoryImpl;
import com.vsp.dicty.storage.model.LangDir;

import java.util.ArrayList;
import java.util.HashMap;

public class LangListInteractorImpl extends AbstractInteractor implements LangListInteractor,
        LanguageRepositoryImpl.LanguageRepositoryCallback {

    private Context mContext;
    private Callback mCallback;

    public LangListInteractorImpl(Executor threadExecutor,
                                  MainThread mainThread,
                                  Callback callback,
                                  Context context) {
        super(threadExecutor, mainThread);

        mContext = context;
        mCallback = callback;
    }

    @Override
    public void run() {
        LanguageRepository languageRepository = new LanguageRepositoryImpl(
                mContext,
                this
        );
        languageRepository.getLanguageList();
    }

    @Override
    public void handleLangListSuccess(final ArrayList<Lang> result) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLangListReceived(result);
            }
        });
    }

    @Override
    public void handleLanguageError(String errorMessage) {

    }
}
