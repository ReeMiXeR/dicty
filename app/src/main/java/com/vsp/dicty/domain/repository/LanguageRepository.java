package com.vsp.dicty.domain.repository;

import com.vsp.dicty.storage.model.LangDir;

public interface LanguageRepository {

    void getUserLanguage();

    void updateCurrentLanguage(String newLang, String langDir);

    LangDir getCurrentLanguage();

    void getLanguageList();

    void getDirsList();

}
