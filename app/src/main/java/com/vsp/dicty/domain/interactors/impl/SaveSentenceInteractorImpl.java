package com.vsp.dicty.domain.interactors.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.base.AbstractInteractor;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.domain.repository.SentenceRepository;
import com.vsp.dicty.storage.SentenceRepositoryImpl;

public class SaveSentenceInteractorImpl extends AbstractInteractor {

    private StorageSentence mStorageSentence;
    private Context mContext;

    public SaveSentenceInteractorImpl(Executor threadExecutor, MainThread mainThread, Context context, StorageSentence storageSentence) {
        super(threadExecutor, mainThread);
        mContext = context;
        mStorageSentence = storageSentence;
    }

    @Override
    public void run() {
        SentenceRepository sentenceRepository = new SentenceRepositoryImpl(mContext);
        sentenceRepository.insertSentenceTranslate(mStorageSentence);
    }
}
