package com.vsp.dicty.storage.model;


import java.util.ArrayList;

public class StorageWord {

    private String lang;
    private String word;
    private String pos;
    private String anm;
    private ArrayList<StorageSubWord> list;

    public String getMainTranslate() {
        if (list.size() > 0)
            return list.get(0).getText();
        else return "";
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getAnm() {
        return anm;
    }

    public void setAnm(String anm) {
        this.anm = anm;
    }

    public ArrayList<StorageSubWord> getList() {
        return list;
    }

    public void setList(ArrayList<StorageSubWord> list) {
        this.list = list;
    }

    public static class StorageSubWord {

        private String text;
        private String pos;
        private ArrayList<StorageSubWordSynonym> synList;
        private ArrayList<StorageSubWordMean> meanList;
        private ArrayList<StorageSubWordEx> exList;

        public String getFormattedSynString() {
            if (synList.size() == 0)
                return "";
            return synList.toString().replace("[", "").replace("]", "");
        }

        public String getFormattedMeanString() {
            if (meanList.size() == 0)
                return "";
            return meanList.toString().replace("[", "(").replace("]", ")");
        }

        public String getFormattedExString() {
            if (exList.size() == 0)
                return "";
            return exList.toString().replace("[", "").replace("]", "").replace(", ", "\n");
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public ArrayList<StorageSubWordSynonym> getSynList() {
            return synList;
        }

        public void setSynList(ArrayList<StorageSubWordSynonym> synList) {
            this.synList = synList;
        }

        public ArrayList<StorageSubWordMean> getMeanList() {
            return meanList;
        }

        public void setMeanList(ArrayList<StorageSubWordMean> meanList) {
            this.meanList = meanList;
        }

        public ArrayList<StorageSubWordEx> getExList() {
            return exList;
        }

        public void setExList(ArrayList<StorageSubWordEx> exList) {
            this.exList = exList;
        }
    }

    public static class StorageSubWordEx {

        private String untranslatedText;
        private String translatedText;

        public String getUntranslatedText() {
            return untranslatedText;
        }

        public void setUntranslatedText(String untranslatedText) {
            this.untranslatedText = untranslatedText;
        }

        public String getTranslatedText() {
            return translatedText;
        }

        public void setTranslatedText(String translatedText) {
            this.translatedText = translatedText;
        }

        @Override
        public String toString() {
            return untranslatedText + " - " + translatedText;
        }
    }

    public static class StorageSubWordSynonym {

        private String text;
        private String pos;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static class StorageSubWordMean {
        private String mean;

        public String getMean() {
            return mean;
        }

        public void setMean(String mean) {
            this.mean = mean;
        }

        @Override
        public String toString() {
            return mean;

        }
    }
}
