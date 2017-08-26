package com.vsp.dicty.presentation.ui.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.vsp.dicty.R;
import com.vsp.dicty.domain.executor.impl.ThreadExecutor;
import com.vsp.dicty.domain.model.Lang;
import com.vsp.dicty.presentation.presenters.LanguagePresenter;
import com.vsp.dicty.presentation.presenters.impl.LanguagePresenterImpl;
import com.vsp.dicty.presentation.ui.adapters.LangListViewAdapter;
import com.vsp.dicty.threading.MainThreadImpl;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LanguagePickerDialog extends DialogFragment implements LanguagePresenter.LangsPickerDialogView, LangListViewAdapter.ListViewAdapterCallback {

    public static final String FROM_LANG = "fromLang";
    public static final String TO_LANG = "toLang";
    public static final String LANG_DIR_DIALOG = "langDirDialog";
    ListView listView;

    private LanguagePresenter mLanguagePresenter;
    private LangListViewAdapter mAdapter;
    private String mLangDir;
    private LanguagePickerDialog.LangPickerCloseListener mCallback;
    AVLoadingIndicatorView loadingView;

    public void setmCallback(LangPickerCloseListener mCallback) {
        this.mCallback = mCallback;
    }

    public void setmLangDir(String mLangDir) {
        this.mLangDir = mLangDir;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mLangDir == null) {
            throw new IllegalArgumentException("missing langDir");
        }

        View rootView = inflater.inflate(R.layout.lang_picker_dialog, container, false);
        loadingView = (AVLoadingIndicatorView) rootView.findViewById(R.id.loading_view_lang_dialog);
        loadingView.show();

        rootView.findViewById(R.id.cancel_lang_picker_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        listView = (ListView) rootView.findViewById(R.id.lang_picker_list_view);
        ButterKnife.bind(rootView);

        mLanguagePresenter = new LanguagePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                getContext()
        );

        mLanguagePresenter.getLangList();

        return rootView;
    }

    @Override
    public void onLangListReceived(ArrayList<Lang> result) {
        loadingView.hide();
        Collections.sort(result);
        mAdapter = new LangListViewAdapter(result, getContext(), this);
        listView.setAdapter(mAdapter);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onLangSelected(String code) {
        mLanguagePresenter.updateCurrentLang(code, mLangDir);
        mCallback.handleDialogClose();
        dismiss();
    }

    public interface LangPickerCloseListener {
        void handleDialogClose();//or whatever args you want
    }
}
