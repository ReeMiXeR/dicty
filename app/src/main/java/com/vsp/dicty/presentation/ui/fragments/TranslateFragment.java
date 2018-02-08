package com.vsp.dicty.presentation.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vsp.dicty.R;
import com.vsp.dicty.domain.executor.impl.ThreadExecutor;
import com.vsp.dicty.storage.model.StorageSentence;
import com.vsp.dicty.presentation.presenters.LanguagePresenter;
import com.vsp.dicty.presentation.presenters.MainPresenter;
import com.vsp.dicty.presentation.presenters.TranslatePresenter;
import com.vsp.dicty.presentation.presenters.impl.LanguagePresenterImpl;
import com.vsp.dicty.presentation.presenters.impl.TranslatePresenterImpl;
import com.vsp.dicty.presentation.ui.adapters.WordRecyclerViewAdapter;
import com.vsp.dicty.presentation.ui.custom.CustomTextWatcher;
import com.vsp.dicty.presentation.ui.dialogs.LanguagePickerDialog;
import com.vsp.dicty.storage.model.LangDir;
import com.vsp.dicty.storage.model.StorageWord;
import com.vsp.dicty.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.Timer;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;


public class TranslateFragment extends Fragment implements MainPresenter.View, TranslatePresenter.View,
        LanguagePresenter.TranslateFragView {

    private final long DELAY = 500;
    @Bind(R.id.untranslated_text_view)
    EditText mUntranslatedTextView;
    @Bind(R.id.main_translate_view)
    TextView mMainTranslateView;
    @Bind(R.id.word_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.translate_progress_layout)
    View mProgressLayout;
    private TranslatePresenter mTranslatePresenter;
    private LanguagePresenter mLanguagePresenter;
    private WordRecyclerViewAdapter mWordAdapter;
    private View mToolbarView;
    private TextView fromLangView;
    private TextView toLangView;
    private LinearLayout toolbarLayout;
    private ImageButton imageButton;
    private ArrayList<StorageWord> mWord;
    private StorageSentence mStorageSentence;
    private boolean lastState;
    private boolean stopTranslate = false;
    private Timer timer = new Timer();

    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translate_fragment, container, false);
        ButterKnife.bind(this, view);
        initToolbar();

        mLanguagePresenter = new LanguagePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                getContext()
        );

        mLanguagePresenter.create();
        return view;
    }

    private void initToolbar() {
        toolbarLayout = (LinearLayout) getActivity().findViewById(R.id.toolbar_layout);

        if (toolbarLayout.indexOfChild(mToolbarView) != -1)
            return;
        LayoutInflater mInflater = LayoutInflater.from(getContext());

        mToolbarView = mInflater.inflate(R.layout.translate_toolbar, null);
        fromLangView = (TextView) mToolbarView.findViewById(R.id.from_lang_view);
        toLangView = (TextView) mToolbarView.findViewById(R.id.to_lang_view);
        fromLangView.setOnClickListener(getOnClickListener(LanguagePickerDialog.FROM_LANG));
        toLangView.setOnClickListener(getOnClickListener(LanguagePickerDialog.TO_LANG));
        imageButton = (ImageButton) mToolbarView.findViewById(R.id.reverse_langs_action);
        toolbarLayout.addView(mToolbarView);
    }

    private View.OnClickListener getOnClickListener(final String langDir) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LanguagePickerDialog dialog = new LanguagePickerDialog();
                dialog.setmLangDir(langDir);
                dialog.setmCallback(new LanguagePickerDialog.LangPickerCloseListener() {
                    @Override
                    public void handleDialogClose() {
                        mLanguagePresenter.getCurrentLang();
                    }
                });
                dialog.show(getFragmentManager(), LanguagePickerDialog.LANG_DIR_DIALOG);
            }
        };
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWordAdapter = new WordRecyclerViewAdapter();
        mRecyclerView.setAdapter(mWordAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mTranslatePresenter = new TranslatePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                getContext()
        );

        mUntranslatedTextView.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void textWasChanged(final Editable editable) {
                MainThreadImpl.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        if (editable.toString().length() <= 1) {
                            mMainTranslateView.setText("");
                            mWordAdapter.clear();
                            stopTranslate = true;
                            return;
                        }
                        stopTranslate = false;
                        StorageSentence text = new StorageSentence();
                        text.setNotTranslatedText(editable.toString());
                        mTranslatePresenter.translateText(text);
                        mProgressLayout.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.e("onResume");
        initToolbar();
        mLanguagePresenter.getCurrentLang();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.e("onPause");
//        if (mWord != null)
//            mTranslatePresenter.saveWord(mWord);
//        else if (mStorageSentence != null)
//            mTranslatePresenter.saveSentence(mStorageSentence);
//        if (lastState)
        //save sentence
//        else if (!lastState)
//            //save word
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
    public void onLangDirReceived(LangDir result) {
        Timber.e("onLangDirReceived");
        if (mToolbarView == null)
            return;
        imageButton.setVisibility(View.VISIBLE);
        fromLangView.setText(result.getFromLang());
        toLangView.setText(result.getToLang());
        mUntranslatedTextView.setText(mUntranslatedTextView.getText().toString());
    }

    @Override
    public void onSentenceTranslated(StorageSentence storageSentence) {
        Timber.e("onSentenceTranslated");
        if (stopTranslate)
            return;
        mProgressLayout.setVisibility(View.GONE);
        mStorageSentence = storageSentence;
        mWord = null;
        mWordAdapter.clear();
        mMainTranslateView.setText(storageSentence.getTranslatedSentence());
    }

    @Override
    public void onWordTranslated(ArrayList<StorageWord> word) {
        Timber.e("onWordTranslated");
        if (word != null) {
            if (word.size() != 0 && !stopTranslate) {
                mProgressLayout.setVisibility(View.GONE);
                mWord = word;
                mStorageSentence = null;
                mMainTranslateView.setText(word.get(0).getMainTranslate());
                mRecyclerView.removeAllViewsInLayout();
                mWordAdapter.setWordArray(word);
            }
        }
    }

    @Override
    public void onTranslateError(String errorMessage) {

    }
}
