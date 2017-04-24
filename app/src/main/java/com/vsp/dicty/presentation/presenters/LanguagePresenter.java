package com.vsp.dicty.presentation.presenters;

import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.presentation.presenters.base.BasePresenter;
import com.vsp.dicty.presentation.ui.BaseView;
import com.vsp.dicty.storage.model.LangDir;

import java.util.ArrayList;
import java.util.HashMap;

public interface LanguagePresenter extends BasePresenter {

    void getCurrentLang();
    void updateCurrentLang(String langCode, String langDir);
    void getLangList();

    interface TranslateFragView extends BaseView {
        void onLangDirReceived(LangDir result);
    }

    interface LangsPickerDialogView extends BaseView {
        void onLangListReceived(ArrayList<Lang> result);
    }
}
