package com.vsp.dicty.domain.model;

public class Lang implements Comparable<Lang> {
    private String langCode;
    private String lang;

    @Override
    public int compareTo(Lang lang) {
        return this.lang.compareToIgnoreCase(lang.getLang());
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    @Override
    public String toString() {
        return lang;
    }
}
