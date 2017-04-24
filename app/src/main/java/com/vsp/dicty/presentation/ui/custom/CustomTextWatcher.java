package com.vsp.dicty.presentation.ui.custom;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public abstract class CustomTextWatcher implements TextWatcher { //Notice abstract class so we leave abstract method textWasChanged() for implementing class to define it

    private Timer timer = new Timer();
    private final int DELAY = 500; //milliseconds of delay for timer

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(final Editable s) {
        timer.cancel();
        timer = new Timer();

        timer.schedule(

                new TimerTask() {
                    @Override
                    public void run() {
                        textWasChanged(s);
                    }
                },
                DELAY

        );
    }
    public abstract void textWasChanged(Editable s);
}

