package com.vsp.dicty.presentation.presenters;

import com.vsp.dicty.domain.model.Sentence;
import com.vsp.dicty.domain.model.UntranslatedText;
import com.vsp.dicty.presentation.presenters.base.BasePresenter;
import com.vsp.dicty.presentation.ui.BaseView;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;

public interface TranslatePresenter extends BasePresenter {

    void saveSentence(Sentence sentence);

    interface View extends BaseView {
        void onSentenceTranslated(Sentence sentence);
        void onWordTranslated(ArrayList<StorageWord> word);
        void onTranslateError(String errorMessage);
    }
    void translateText(UntranslatedText text);
}
