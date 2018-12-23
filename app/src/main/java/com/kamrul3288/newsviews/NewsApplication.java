package com.kamrul3288.newsviews;

import android.app.Application;
import android.content.Context;

import com.kamrul3288.newsviews.di.ApplicationComponent;
import com.kamrul3288.newsviews.di.ApplicationModule;
import com.kamrul3288.newsviews.di.ContextModule;
import com.kamrul3288.newsviews.di.DaggerApplicationComponent;
import com.kamrul3288.newsviews.di.OkHttpClientModule;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class NewsApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .applicationModule(new ApplicationModule())
                .okHttpClientModule(new OkHttpClientModule())
                .build();



    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
