package com.vsp.dicty.domain.model;

public class Sentence extends UntranslatedText {

    private String mTranslatedSentence;
    private String mToLang;

    public Sentence() {
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
}
