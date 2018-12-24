package com.kamrul3288.newsviews.view.splashscreen;

public interface SplashScreenContract {

    interface SplashScreenView{
        void showWelcomeScreen();
        void showSplashScreen();
        void navigateNextScreen();
    }

    interface SplashScreenPresenter{
        void loadScreen();
        void onDestroy();
        void waitForSplashScreen();
    }

}
