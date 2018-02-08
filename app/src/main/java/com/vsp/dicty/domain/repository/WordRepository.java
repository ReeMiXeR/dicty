package com.vsp.dicty.domain.repository;

import com.vsp.dicty.storage.WordRepositoryImpl;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;

public interface WordRepository {

    boolean insertWordTranslate();

    boolean updateWordTranslate();

    ArrayList<StorageWord> getWordTranslate(StorageSentence text, WordRepositoryImpl.WordRepositoryCallback callback);

    boolean delete();
}
