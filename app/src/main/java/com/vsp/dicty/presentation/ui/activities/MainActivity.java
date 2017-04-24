package com.vsp.dicty.presentation.ui.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vsp.dicty.R;
import com.vsp.dicty.presentation.presenters.MainPresenter;
import com.vsp.dicty.presentation.ui.fragments.SecondFragment;
import com.vsp.dicty.presentation.ui.fragments.ThirdFragment;
import com.vsp.dicty.presentation.ui.fragments.TranslateFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.View, BottomNavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.bottom_navigation_menu)
    BottomNavigationView mBottomNavMenu;

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        mBottomNavMenu.setOnNavigationItemSelectedListener(this);
        onNavigationItemSelected(mBottomNavMenu.getMenu().findItem(R.id.nav_translate));
    }



    @Override
    protected void onResume() {
        super.onResume();

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.nav_translate:
                fragment = TranslateFragment.newInstance();
                break;
            case R.id.nav_history:
                fragment = SecondFragment.newInstance();
                break;
            default:
                fragment = TranslateFragment.newInstance();
        }
        pushFragment(fragment);
        return true;
    }

    public void pushFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();

        List<Fragment> fragments = fm.getFragments();
        FragmentTransaction ft = fm.beginTransaction();
        boolean fragNotShown = true;

        if (fragments != null) {
            for (int i = fragments.size() - 1; i >= 0; i--)
                if (fragments.get(i) != null && fragments.get(i).isVisible()) {
                    ft.hide(fragments.get(i));
                    fragments.get(i).onPause();
                    break;
                }
            for (Fragment fragmentFromStack : fragments) {
                if (fragmentFromStack != null) {
                    if (fragmentFromStack.getClass().getSimpleName().equals(fragment.getClass().getSimpleName())) {
                        fragNotShown = false;
                        ft.setCustomAnimations(getFragEnterAnimation(fragment), getFragExitAnimation(fragment));
                        ft.show(fragmentFromStack);
                        ft.commit();
                        fragmentFromStack.onResume();
                    }
                }
            }
        }
        if (fragNotShown) {
            ft.add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                    .setCustomAnimations(getFragEnterAnimation(fragment), getFragExitAnimation(fragment))
                    .show(fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    public int getFragEnterAnimation(Fragment fragment) {
        String name = fragment.getClass().getSimpleName();
        if (name.equals(TranslateFragment.class.getSimpleName()))
            return R.anim.enter_from_left;
        else if (name.equals(SecondFragment.class.getSimpleName()))
            return R.anim.enter_from_right;
        return R.anim.enter_from_right;
    }

    public int getFragExitAnimation(Fragment fragment) {
        String name = fragment.getClass().getSimpleName();
        if (name.equals(TranslateFragment.class.getSimpleName()))
            return R.anim.exit_to_left;
        else if (name.equals(SecondFragment.class.getSimpleName()))
            return R.anim.exit_to_right;
        return R.anim.exit_to_right;
    }
}
