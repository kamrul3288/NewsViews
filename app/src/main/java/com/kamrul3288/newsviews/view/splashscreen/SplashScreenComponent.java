package com.kamrul3288.newsviews.view.splashscreen;

import com.kamrul3288.newsviews.di.ApplicationComponent;

import dagger.Component;

@SplashScope
@Component(modules = SplashScreenModule.class, dependencies = ApplicationComponent.class)
public interface SplashScreenComponent {
    void inject(SplashScreenActivity activity);
}
