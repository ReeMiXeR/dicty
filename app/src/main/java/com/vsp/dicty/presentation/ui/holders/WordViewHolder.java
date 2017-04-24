package com.vsp.dicty.presentation.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vsp.dicty.R;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;


public class WordViewHolder extends RecyclerView.ViewHolder {

    LinearLayout mView;

    private StorageWord mWord;
    private ArrayList<StorageWord.StorageSubWord> mSubArray;

    public WordViewHolder(View view) {
        super(view);
        mView = (LinearLayout) view;
    }

    public void build(StorageWord word) {
        mWord = word;
        mSubArray = word.getList();

        ((TextView)mView.findViewById(R.id.part_if_speech_view)).setText(word.getPos());

        int i = 1;
        for (StorageWord.StorageSubWord subWord : mSubArray){
            View view = LayoutInflater.from(mView.getContext()).inflate(R.layout.tr_word_recycler_item, null, false);
            TextView synView = ((TextView)view.findViewById(R.id.syn_view));
            TextView meanView = ((TextView)view.findViewById(R.id.mean_view));
            TextView exView = ((TextView)view.findViewById(R.id.ex_view));

            String synText = subWord.getText();
            if (subWord.getSynList() != null)
                synText = synText.concat(", " + subWord.getFormattedSynString());

            String meanText = "";
            if (subWord.getMeanList() != null)
                meanText = subWord.getFormattedMeanString();

            String exText = "";
            if (subWord.getExList() != null)
                exText = subWord.getFormattedExString();

            ((TextView)view.findViewById(R.id.subword_number)).setText(Integer.toString(i));
            i++;
            if (synText.length() > 0) {
                synView.setText(synText);
                synView.setVisibility(View.VISIBLE);
            }
            if (meanText.length() > 0) {
                meanView.setText(meanText);
                meanView.setVisibility(View.VISIBLE);
            }
            if (exText.length() > 0) {
                exView.setText(exText);
                exView.setVisibility(View.VISIBLE);
            }
            mView.addView(view);
        }

    }
}
