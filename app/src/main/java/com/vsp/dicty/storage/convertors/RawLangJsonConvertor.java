package com.vsp.dicty.storage.convertors;

import android.content.Context;

import com.vsp.dicty.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class RawLangJsonConvertor {

    public static JSONObject getRuLangs(Context context) {
        return getJsonFromRes(context, R.raw.ru_sentence_langs);
    }

    public static JSONObject getEnLangs(Context context) {
        return getJsonFromRes(context, R.raw.en_sentence_langs);
    }

    public static JSONObject getLangDirs(Context context) {
        return getJsonFromRes(context, R.raw.sentence_dirs);
    }

    private static JSONObject getJsonFromRes(Context context, int id) {
        InputStream is = context.getResources().openRawResource(id);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String jsonString = writer.toString();
        JSONObject jsonArray = null;
        try {
            if (jsonString != null) {
                jsonArray = new JSONObject(jsonString);
            }
        }catch (JSONException ex) {
            ex.printStackTrace();
        }
        return jsonArray;
    }

}
