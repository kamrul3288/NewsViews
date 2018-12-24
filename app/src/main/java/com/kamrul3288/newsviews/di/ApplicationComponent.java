package com.kamrul3288.newsviews.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application application);
    @Named("news_api")
    ApiInterfaces provideApiInterface();
    @Named("number_api")
    ApiInterfaces provideApiInterface2();
    SharedPreferences provideSharedPreference();
    SharedPreferences.Editor provideSharedPreferenceEditor();
}

