package com.kamrul3288.newsviews.di;

import android.app.Application;
import android.content.Context;

import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Application application);
}
