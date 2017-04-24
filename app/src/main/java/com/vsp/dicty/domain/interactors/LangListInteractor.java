package com.vsp.dicty.domain.interactors;

import com.vsp.dicty.domain.interactors.base.Interactor;
import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.storage.model.LangDir;

import java.util.ArrayList;
import java.util.HashMap;

public interface LangListInteractor extends Interactor {

    interface Callback {

        void onLangListReceived(ArrayList<Lang> result);

        void onLanguageError(String error);
    }
}
