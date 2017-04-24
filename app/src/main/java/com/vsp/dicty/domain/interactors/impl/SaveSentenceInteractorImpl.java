package com.vsp.dicty.domain.interactors.impl;

import android.content.Context;

import com.vsp.dicty.domain.executor.Executor;
import com.vsp.dicty.domain.executor.MainThread;
import com.vsp.dicty.domain.interactors.base.AbstractInteractor;
import com.vsp.dicty.domain.model.Sentence;
import com.vsp.dicty.domain.repository.SentenceRepository;
import com.vsp.dicty.storage.SentenceRepositoryImpl;

public class SaveSentenceInteractorImpl extends AbstractInteractor {

    private Sentence mSentence;
    private Context mContext;

    public SaveSentenceInteractorImpl(Executor threadExecutor, MainThread mainThread, Context context, Sentence sentence) {
        super(threadExecutor, mainThread);
        mContext = context;
        mSentence = sentence;
    }

    @Override
    public void run() {
        SentenceRepository sentenceRepository = new SentenceRepositoryImpl(mContext);
        sentenceRepository.insertSentenceTranslate(mSentence);
    }
}
