package com.vsp.dicty.storage;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pixplicity.easyprefs.library.Prefs;
import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.domain.repository.LanguageRepository;
import com.vsp.dicty.network.RestSentenceClient;
import com.vsp.dicty.network.model.RestSentence;
import com.vsp.dicty.storage.convertors.RawLangJsonConvertor;
import com.vsp.dicty.storage.model.LangDir;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import timber.log.Timber;

import static com.vsp.dicty.presentation.ui.dialogs.LanguagePickerDialog.FROM_LANG;
import static com.vsp.dicty.presentation.ui.dialogs.LanguagePickerDialog.TO_LANG;

public class LanguageRepositoryImpl implements LanguageRepository, RestSentenceClient.RestLanguageResultCallback {

    public final static String CURRENT_LANG_DIR = "CurrentLangDir";
    public final static String DEFAULT_LANG_DIR = "ru-en";
    public final static String LANG_DIRS = "lang_dirs";
    public final static String LANG = "_lang";
    private Context mContext;
    private LanguageRepositoryCallback mCallback;

    public LanguageRepositoryImpl(Context context) {
        mContext = context;
    }

    public LanguageRepositoryImpl(Context context, LanguageRepositoryCallback callback) {
        mContext = context;
        mCallback = callback;
    }

    @Override
    public void handleRestSentenceSuccess(RestSentence result) {
//        mCallback.handleCurrentSuccess(StorageSentenceConverter.convertRestToStorage(result));
    }

    @Override
    public void getUserLanguage() {

    }

    @Override
    public void updateCurrentLanguage(String newLang, String langDir) {
        String langDirString = Prefs.getString(CURRENT_LANG_DIR, DEFAULT_LANG_DIR);
        String newLangDirString = "";
        if (langDir.equals(FROM_LANG))
            newLangDirString = newLang + langDirString.substring(2, 5);
        if (langDir.equals(TO_LANG))
            newLangDirString = langDirString.substring(0, 3) + newLang;
        Prefs.putString(CURRENT_LANG_DIR, newLangDirString);
        Timber.e("New lang dir is - " + newLangDirString);
    }

    @Override
    public LangDir getCurrentLanguage() {
        setupFirstOpen();
        String langDirString = Prefs.getString(CURRENT_LANG_DIR, DEFAULT_LANG_DIR);
        LangDir langDir = new LangDir();
        langDir.setDir(langDirString);
        langDir.setFromLang(getFromLangString(langDirString));
        langDir.setToLang(getToLangString(langDirString));
        return langDir;
    }

    private String getFromLangString(String langDirString) {
        String fromLang = "";
        try {
            JSONObject jsonObject = new JSONObject(Prefs.getString("ru" + LANG, "error"));
            fromLang = jsonObject.get(langDirString.substring(0, 2)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fromLang;
    }

    private String getToLangString(String langDirString) {
        String fromLang = "";
        try {
            JSONObject jsonObject = new JSONObject(Prefs.getString("ru" + LANG, "error"));
            fromLang = jsonObject.get(langDirString.substring(3, 5)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fromLang;
    }

    private void setupFirstOpen() {
        if (Prefs.getBoolean("firstLangSetup", true)) {
            JSONObject jsonDirs = RawLangJsonConvertor.getLangDirs(mContext);
            JSONObject jsonRuLangs = RawLangJsonConvertor.getRuLangs(mContext);
            JSONObject jsonEnLangs = RawLangJsonConvertor.getEnLangs(mContext);
            try {
                Prefs.putString(LANG_DIRS, jsonDirs.get("dirs").toString());
                Prefs.putString("ru" + LANG, jsonRuLangs.get("langs").toString());
                Prefs.putString("en" + LANG, jsonEnLangs.get("langs").toString());
            } catch (JSONException e) {
                Timber.e("Failed on raw json parse");
                e.printStackTrace();
            }
        }
        Prefs.putBoolean("firstLangSetup", false);
    }

    @Override
    public void getLanguageList() {
        String deviceLang = mContext.getResources().getConfiguration().locale.getLanguage();
        String langString = "";
        HashMap<String, Object> map = null;
        if (deviceLang.compareToIgnoreCase("ru") == 0) {
            langString = Prefs.getString("ru" + LANG, "");
        }
        try {
            map = new ObjectMapper().readValue(langString, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Lang> langList = new ArrayList<>();
        if (map != null) {
            for (String key : map.keySet()) {
                Lang lang = new Lang();
                lang.setLangCode(key);
                langList.add(lang);
            }
            int i = 0;
            for (Object value : map.values()) {
                langList.get(i).setLang((String) value);
                i++;
            }
        }
        mCallback.handleLangListSuccess(langList);
    }

    @Override
    public void getDirsList() {

    }

    @Override
    public void handleRestSentenceError(String errorMessage) {
        mCallback.handleLanguageError(errorMessage);
    }

    public interface LanguageRepositoryCallback {

        void handleLangListSuccess(ArrayList<Lang> result);

        void handleLanguageError(String errorMessage);
    }
}
