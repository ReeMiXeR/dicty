package com.vsp.dicty.presentation.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vsp.dicty.R;
import com.vsp.dicty.presentation.presenters.MainPresenter;


public class SecondFragment extends Fragment implements MainPresenter.View {

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout toolbarLayout = (LinearLayout) getActivity().findViewById(R.id.toolbar_layout);

        toolbarLayout.removeAllViewsInLayout();
        return inflater.inflate(R.layout.second_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LinearLayout toolbarLayout = (LinearLayout) getActivity().findViewById(R.id.toolbar_layout);
        toolbarLayout.removeAllViewsInLayout();
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
}
