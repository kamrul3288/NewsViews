package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;

import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

public interface MainScreenContract {
    interface MainScreenView{
        void showProgressBar();
        void hideProgressBar();
        void showNews(NewsList newsList);
        void showNetworkError(String message);
    }

    interface MainScreenPresenter{
        void loadNews();
        void onDestroy();
    }


    interface MainScreenModel{
        interface OnFinishListener{
            void onNewsLoadSuccess(NewsList newsList);
            void onNetworkError(String message);
        }

        void loadNews(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity);
    }

}
