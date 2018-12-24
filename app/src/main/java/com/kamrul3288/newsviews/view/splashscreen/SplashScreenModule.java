package com.kamrul3288.newsviews.view.splashscreen;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;

@Module
public class SplashScreenModule {
    private final Activity activity;
    private final SplashScreenContract.SplashScreenView view;

    public SplashScreenModule(Activity activity, SplashScreenContract.SplashScreenView view) {
        this.activity = activity;
        this.view = view;
    }

    @SplashScope
    @Provides
    Activity activity(){
        return activity;
    }

    @SplashScope
    @Provides
    SplashScreenContract.SplashScreenView view(){
        return view;
    }

}
