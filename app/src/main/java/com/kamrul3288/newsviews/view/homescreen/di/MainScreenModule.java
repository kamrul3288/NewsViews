package com.kamrul3288.newsviews.view.homescreen.di;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;

import com.kamrul3288.newsviews.adapter.NewsAdepter;
import com.kamrul3288.newsviews.view.homescreen.MainScreenContract;

import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModule {
    private final Activity activity;
    private final MainScreenContract.MainScreenView view;


    public MainScreenModule(Activity activity, MainScreenContract.MainScreenView view) {
        this.activity = activity;
        this.view = view;
    }

    @MainScreenScope
    @Provides
    Activity activity() {
        return activity;
    }

    @MainScreenScope
    @Provides
    GridLayoutManager gridLayoutManager(Activity activity) {
        return new GridLayoutManager(activity, 2);
    }

    @MainScreenScope
    @Provides
    NewsAdepter adepter(Activity activity){
        return new NewsAdepter(activity);
    }

    @MainScreenScope
    @Provides
    MainScreenContract.MainScreenView view(){
        return view;
    }

}

