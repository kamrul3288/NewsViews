package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;

import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import javax.inject.Inject;

public class MainScreenPresenterImpl implements MainScreenContract.MainScreenPresenter, MainScreenContract.MainScreenModel.OnFinishListener {


    private MainScreenContract.MainScreenView view;
    private MainScreenContract.MainScreenModel mainScreenModel;
    private ApiInterfaces apiInterfaces;
    private Activity activity;

    @Inject
    public MainScreenPresenterImpl(MainScreenContract.MainScreenView view, ApiInterfaces apiInterfaces,Activity activity) {
        this.view = view;
        this.apiInterfaces = apiInterfaces;
        mainScreenModel = new MainScreenModelImpl();
        this.activity = activity;
    }

    @Override
    public void loadNews() {
        if (view != null){
            view.showProgressBar();
            mainScreenModel.loadNews(this,apiInterfaces,activity);
        }
    }

    @Override
    public void onDestroy() {
        if (view != null){
            view = null;
        }
    }

    @Override
    public void onNewsLoadSuccess(NewsList newsList) {
        if (view != null){
            view.hideProgressBar();
            view.showNews(newsList);
        }
    }

    @Override
    public void onNetworkError(String message) {
        if (view != null){
            view.hideProgressBar();
            view.showNetworkError(message);
        }
    }
}
