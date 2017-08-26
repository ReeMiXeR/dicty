package com.vsp.dicty.storage.convertors;

import com.vsp.dicty.network.model.RestWord;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;


public class StorageWordConverter {

    public static ArrayList<StorageWord> convertRestToStorage(RestWord restWord) {

        ArrayList<StorageWord> arrayList = new ArrayList<>();

        for (RestWord.Def def : restWord.getDef()) {
            StorageWord storageWord = new StorageWord();
            storageWord.setWord(def.getText());
            storageWord.setPos(def.getPos());
            storageWord.setAnm(def.getTs());

            ArrayList<StorageWord.StorageSubWord> subWords = new ArrayList<>();
            for (RestWord.Tr tr : def.getTr()) {
                //SubWord
                StorageWord.StorageSubWord subWord = new StorageWord.StorageSubWord();
                subWord.setText(tr.getText());
                subWord.setPos(tr.getPos());

                if (tr.getSyn() != null) {
                    ArrayList<StorageWord.StorageSubWordSynonym> subWordSynList = new ArrayList<>();
                    for (RestWord.Syn syn : tr.getSyn()) {
                        StorageWord.StorageSubWordSynonym subWordSyn = new StorageWord.StorageSubWordSynonym();
                        subWordSyn.setText(syn.getText());
                        subWordSyn.setPos(syn.getPos());
                        subWordSynList.add(subWordSyn);
                    }
                    subWord.setSynList(subWordSynList);
                }

                if (tr.getMean() != null) {
                    ArrayList<StorageWord.StorageSubWordMean> means = new ArrayList<>();
                    for (RestWord.Mean mean : tr.getMean()) {
                        StorageWord.StorageSubWordMean storageMean = new StorageWord.StorageSubWordMean();
                        storageMean.setMean(mean.getText());
                        means.add(storageMean);
                    }
                    subWord.setMeanList(means);
                }

                if (tr.getEx() != null) {
                    ArrayList<StorageWord.StorageSubWordEx> examples = new ArrayList<>();
                    for (RestWord.Ex ex : tr.getEx()) {
                        StorageWord.StorageSubWordEx subWordEx = new StorageWord.StorageSubWordEx();
                        subWordEx.setUntranslatedText(ex.getText());
                        subWordEx.setTranslatedText(ex.getTr().get(0).getText());
                        examples.add(subWordEx);
                    }
                    subWord.setExList(examples);
                }
                subWords.add(subWord);
            }
            storageWord.setList(subWords);
            arrayList.add(storageWord);
        }
        return arrayList;
    }

}
