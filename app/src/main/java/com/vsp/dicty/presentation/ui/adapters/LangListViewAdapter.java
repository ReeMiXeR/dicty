package com.vsp.dicty.presentation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vsp.dicty.R;
import com.vsp.dicty.domain.executor.impl.ThreadExecutor;
import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.presentation.presenters.LanguagePresenter;
import com.vsp.dicty.presentation.presenters.impl.LanguagePresenterImpl;
import com.vsp.dicty.presentation.ui.dialogs.LanguagePickerDialog;
import com.vsp.dicty.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.Map;

import timber.log.Timber;


public class LangListViewAdapter extends BaseAdapter {

    private ArrayList<Lang> mData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private LangListViewAdapter.ListViewAdapterCallback mCallback;

    public LangListViewAdapter(ArrayList<Lang> mData, Context context, LangListViewAdapter.ListViewAdapterCallback callback) {
        this.mData = mData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mCallback = callback;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.get(i).hashCode();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        String title = mData.get(i).toString();

        View view = convertView;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.lang_listview_item, viewGroup, false);
        }
        ((TextView)view.findViewById(R.id.lang_text_view)).setText(title);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.e("Lang dialog item click");
                final int position = ((ListView)view.getParent()).getPositionForView(view);
                mCallback.onLangSelected(mData.get(position).getLangCode());
            }
        });


        return view;
    }

    public interface ListViewAdapterCallback {
        void onLangSelected(String code);
    }
}
