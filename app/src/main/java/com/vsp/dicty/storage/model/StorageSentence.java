package com.vsp.dicty.storage.model;

import io.realm.RealmObject;

public class StorageSentence extends RealmObject {

    private String mTranslatedSentence;
    private String mToLang;
    private String mNotTranslatedText;
    private String mFromLang;

    public StorageSentence() {
    }

    // constructor for dummy data


    public String getTranslatedSentence() {
        return mTranslatedSentence;
    }

    public void setTranslatedSentence(String translatedSentence) {
        mTranslatedSentence = translatedSentence;
    }

    public String getToLang() {
        return mToLang;
    }

    public void setToLang(String toLang) {
        mToLang = toLang;
    }

    public String getNotTranslatedText() {
        return mNotTranslatedText;
    }

    public void setNotTranslatedText(String notTranslatedText) {
        mNotTranslatedText = notTranslatedText;
    }

    public String getFromLang() {
        return mFromLang;
    }

    public void setFromLang(String fromLang) {
        mFromLang = fromLang;
    }
}
