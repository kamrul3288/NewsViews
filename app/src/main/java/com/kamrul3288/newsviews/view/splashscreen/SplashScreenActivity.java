package com.kamrul3288.newsviews.view.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.github.paolorotolo.appintro.AppIntro;
import com.kamrul3288.newsviews.NewsApplication;
import com.kamrul3288.newsviews.constant.Constants;
import com.kamrul3288.newsviews.fragment.WelcomeScreenPage1;
import com.kamrul3288.newsviews.fragment.WelcomeScreenPage2;
import com.kamrul3288.newsviews.fragment.WelcomeScreenPage3;
import com.kamrul3288.newsviews.view.homescreen.MainActivity;

import javax.inject.Inject;

public class SplashScreenActivity extends AppIntro implements SplashScreenContract.SplashScreenView {

    @Inject
    Activity activity;

    @Inject
    SplashScreenPresenterImpl splashScreenPresenter;

    @Inject
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreenComponent component = DaggerSplashScreenComponent.builder()
                .applicationComponent(((NewsApplication)getApplication()).getApplicationComponent())
                .splashScreenModule(new SplashScreenModule(this,this))
                .build();
        component.inject(this);
        splashScreenPresenter.loadScreen();

    }

    @Override
    public void showWelcomeScreen() {
        addSlide(WelcomeScreenPage1.getInstnace());
        addSlide(WelcomeScreenPage2.getInstance());
        addSlide(WelcomeScreenPage3.getInstance());
        showSkipButton(false);
        setSeparatorColor(Color.parseColor("#ffffff"));

    }

    @Override
    public void showSplashScreen() {
        addSlide(WelcomeScreenPage2.getInstance());
        showSkipButton(false);
        showSeparator(false);
        showPagerIndicator(false);
        setDoneText("");
        splashScreenPresenter.waitForSplashScreen();
    }

    @Override
    public void navigateNextScreen() {
        startActivity(new Intent(activity,MainActivity.class));
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        editor.putString(Constants.IS_SHOW_WELCOME_SCREEN,"false");
        editor.apply();
        navigateNextScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()){
            splashScreenPresenter.onDestroy();
        }
    }
}
