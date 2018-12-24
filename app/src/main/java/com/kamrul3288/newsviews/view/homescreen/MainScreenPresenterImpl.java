package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;
import android.app.AlertDialog;
import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import javax.inject.Inject;

public class MainScreenPresenterImpl implements MainScreenContract.MainScreenPresenter, MainScreenContract.MainScreenModel.OnFinishListener {


    private MainScreenContract.MainScreenView view;
    private MainScreenContract.MainScreenModel mainScreenModel;
    private ApiInterfaces apiInterfaces;
    private Activity activity;
    private AlertDialog.Builder builder;

    @Inject
    public MainScreenPresenterImpl(MainScreenContract.MainScreenView view, ApiInterfaces apiInterfaces,Activity activity,AlertDialog.Builder builder) {
        this.view = view;
        this.apiInterfaces = apiInterfaces;
        mainScreenModel = new MainScreenModelImpl();
        this.activity = activity;
        this.builder = builder;
    }

    @Override
    public void loadNews() {
        if (view != null){
            view.showProgressBar();
            mainScreenModel.loadNews(this,apiInterfaces,activity);
        }
    }

    @Override
    public void showExitDialog() {
       if (view != null){
           mainScreenModel.showExitDialog(builder,this);
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

    @Override
    public void onExitPositive() {
        if (view != null){
            view.exitFromApp();
        }
    }


}
