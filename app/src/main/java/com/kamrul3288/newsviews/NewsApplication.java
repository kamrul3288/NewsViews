package com.kamrul3288.newsviews;

import android.app.Application;
import android.content.Context;
import com.kamrul3288.newsviews.di.ApplicationComponent;
import com.kamrul3288.newsviews.di.ApplicationModule;
import com.kamrul3288.newsviews.di.ContextModule;
import com.kamrul3288.newsviews.di.DaggerApplicationComponent;
import com.kamrul3288.newsviews.di.OkHttpClientModule;


public class NewsApplication extends Application {

    private ApplicationComponent applicationComponent;



    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .applicationModule(new ApplicationModule())
                .okHttpClientModule(new OkHttpClientModule())
                .build();
        applicationComponent.inject(this);



    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
