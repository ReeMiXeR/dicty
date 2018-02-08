package com.vsp.dicty.domain.repository;

import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.storage.SentenceRepositoryImpl;


public interface SentenceRepository {

    boolean insertSentenceTranslate(StorageSentence model);

    boolean updateSentenceTranslate(StorageSentence model);

    void getSentenceTranslate(StorageSentence sentence, SentenceRepositoryImpl.SentenceRepositoryCallback callback);

    boolean delete(StorageSentence model);
}
