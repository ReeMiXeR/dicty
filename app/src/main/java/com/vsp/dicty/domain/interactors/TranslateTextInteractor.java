package com.vsp.dicty.domain.interactors;

import com.vsp.dicty.domain.interactors.base.Interactor;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;


public interface TranslateTextInteractor extends Interactor {

    interface TranslateCallback {
        void onWordTranslated(ArrayList<StorageWord> word);

        void onSentenceTranslated(StorageSentence storageSentence);

        void onTranslateError(String error);
    }


}
