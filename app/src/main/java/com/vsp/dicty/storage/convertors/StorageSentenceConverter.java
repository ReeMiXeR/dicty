package com.vsp.dicty.storage.convertors;

import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.network.model.RestSentence;

public class StorageSentenceConverter {

    public static StorageSentence convertRestToStorage(RestSentence restSentence) {
        StorageSentence storageSentence = new StorageSentence();
        storageSentence.setFromLang(restSentence.getLang());
        storageSentence.setToLang(restSentence.getLang());
        storageSentence.setNotTranslatedText(restSentence.getText().get(0));
        storageSentence.setTranslatedSentence(restSentence.getText().get(0));
        return storageSentence;
    }
}
