package com.vsp.dicty.domain.interactors;

import com.vsp.dicty.domain.interactors.base.Interactor;
import com.vsp.dicty.domain.model.Sentence;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;


public interface TranslateTextInteractor extends Interactor {

    interface TranslateCallback {
        void onWordTranslated(ArrayList<StorageWord> word);

        void onSentenceTranslated(Sentence sentence);

        void onTranslateError(String error);
    }


}
