package com.vsp.dicty.storage.convertors;

import com.vsp.dicty.domain.model.Sentence;
import com.vsp.dicty.network.model.RestSentence;

public class StorageSentenceConverter {

    public static Sentence convertRestToStorage(RestSentence restSentence) {
        Sentence sentence = new Sentence();
        sentence.setFromLang(restSentence.getLang());
        sentence.setToLang(restSentence.getLang());
        sentence.setNotTranslatedText(restSentence.getText().get(0));
        sentence.setTranslatedSentence(restSentence.getText().get(0));
        return sentence;
    }
}
