package com.kamrul3288.newsviews.view.homescreen.di;

import com.kamrul3288.newsviews.di.ApplicationComponent;
import com.kamrul3288.newsviews.view.homescreen.MainActivity;

import dagger.Component;

@MainScreenScope
@Component(modules = MainScreenModule.class, dependencies = ApplicationComponent.class)
public interface MainScreenComponent {
    void inject(MainActivity activity);
}
