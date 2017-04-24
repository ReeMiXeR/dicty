package com.vsp.dicty.domain.repository;

import com.vsp.dicty.domain.model.Sentence;
import com.vsp.dicty.domain.model.UntranslatedText;
import com.vsp.dicty.storage.SentenceRepositoryImpl;


public interface SentenceRepository {

    boolean insertSentenceTranslate(Sentence model);

    boolean updateSentenceTranslate(Sentence model);

    void getSentenceTranslate(UntranslatedText sentence, SentenceRepositoryImpl.SentenceRepositoryCallback callback);

    boolean delete(Sentence model);
}
