package com.vsp.dicty.domain.interactors.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.UpdateLangInteractor;
import com.vsp.dicty.domain.interactors.base.AbstractInteractor;
import com.vsp.dicty.domain.repository.LanguageRepository;
import com.vsp.dicty.storage.LanguageRepositoryImpl;


public class UpdateLangInteractorImpl extends AbstractInteractor {

    private Context mContext;
    private String mNewLangCode;
    private String mDirPos;
    private UpdateLangInteractor.Callback mCallback;

    public UpdateLangInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    Context context,
                                    String newLangCode,
                                    String langDir) {
        super(threadExecutor, mainThread);
        mContext = context;
        mNewLangCode = newLangCode;
        mDirPos = langDir;
    }

    @Override
    public void run() {
        LanguageRepository languageRepository = new LanguageRepositoryImpl(mContext);
        languageRepository.updateCurrentLanguage(mNewLangCode, mDirPos);
    }
}
