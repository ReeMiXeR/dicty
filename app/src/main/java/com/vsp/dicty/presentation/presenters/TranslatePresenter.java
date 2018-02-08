package com.vsp.dicty.presentation.presenters;

import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.presentation.presenters.base.BasePresenter;
import com.vsp.dicty.presentation.ui.BaseView;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;

public interface TranslatePresenter extends BasePresenter {

    void saveSentence(StorageSentence storageSentence);

    interface View extends BaseView {
        void onSentenceTranslated(StorageSentence storageSentence);
        void onWordTranslated(ArrayList<StorageWord> word);
        void onTranslateError(String errorMessage);
    }
    void translateText(StorageSentence text);
}
