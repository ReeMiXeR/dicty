package com.vsp.dicty.presentation.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsp.dicty.R;
import com.vsp.dicty.presentation.ui.holders.WordViewHolder;
import com.vsp.dicty.storage.model.StorageWord;

import java.util.ArrayList;


public class WordRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<StorageWord> mWordArray;

    public WordRecyclerViewAdapter() {
        mWordArray = new ArrayList<>();
    }

    public void setWordArray(ArrayList<StorageWord> wordArray) {
        mWordArray = wordArray;
        notifyDataSetChanged();
    }

    public void clear() {
        int size = this.mWordArray.size();
        this.mWordArray.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_word_recycler_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null)
            ((WordViewHolder) holder).build(mWordArray.get(position));
    }

    @Override
    public int getItemCount() {
        return mWordArray.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return mWordArray.get(position).hashCode();
    }


}
