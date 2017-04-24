package com.vsp.dicty.domain.interactors;

import com.vsp.dicty.domain.interactors.base.Interactor;
import com.vsp.dicty.storage.model.LangDir;

public interface CurrentLangInteractor extends Interactor {

    interface Callback {
        void onCurrLangReceived(LangDir result);
    }
}
