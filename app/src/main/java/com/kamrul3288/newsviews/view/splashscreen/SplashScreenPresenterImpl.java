package com.kamrul3288.newsviews.view.splashscreen;

import android.content.SharedPreferences;
import android.os.Handler;

import com.kamrul3288.newsviews.constant.Constants;


import javax.inject.Inject;

public class SplashScreenPresenterImpl implements SplashScreenContract.SplashScreenPresenter{

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SplashScreenContract.SplashScreenView view;
    private Handler handler;

    @Inject
    public SplashScreenPresenterImpl(SharedPreferences sharedPreferences, SharedPreferences.Editor editor, SplashScreenContract.SplashScreenView view) {
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        this.view = view;
    }

    @Override
    public void loadScreen() {
        String showWelcomeScreenStatus = sharedPreferences.getString(Constants.IS_SHOW_WELCOME_SCREEN,"true");
        if (showWelcomeScreenStatus.equalsIgnoreCase("true")){
            if (view != null){
                view.showWelcomeScreen();
            }
        }else {
            if (view != null){
                view.showSplashScreen();
            }
        }

    }

    @Override
    public void onDestroy() {

        if (handler != null){
            handler.removeCallbacks(null);
        }

        if (view != null){
            view = null;
        }
    }

    @Override
    public void waitForSplashScreen() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.navigateNextScreen();
            }
        },2000);
    }
}
