package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;
import android.app.AlertDialog;

import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

public interface MainScreenContract {
    interface MainScreenView{
        void showProgressBar();
        void hideProgressBar();
        void showNews(NewsList newsList);
        void showNetworkError(String message);
        void exitFromApp();
        void showNumberAndDateResultInfo(String result);
    }

    interface MainScreenPresenter{
        void loadNews();
        void showExitDialog();
        void loadNumberApi(String query);
        void onDestroy();
    }


    interface MainScreenModel{
        interface OnFinishListener{
            void onNewsLoadSuccess(NewsList newsList);
            void onNetworkError(String message);
            void onExitPositive();
            void onNumberAndDateResultInfo(String result);
        }

        void loadNews(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity);
        void showExitDialog(AlertDialog.Builder builder,OnFinishListener listener);
        void loadNumberInfo(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity,String number);
        void loadDateInfo(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity,String query);

    }

}
