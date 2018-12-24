package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.constant.Constants;
import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenModelImpl implements MainScreenContract.MainScreenModel{

    @Override
    public void loadNews(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity) {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd",Locale.US);
        String fromDate = df.format(c);
        Call<NewsList> call = apiInterfaces.getNews("bitcoin",fromDate,"publishedAt",Constants.NEWS_API_KEY);
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(@NonNull Call<NewsList> call, @NonNull Response<NewsList> response) {
                if (response.isSuccessful() && response.body() != null){
                    listener.onNewsLoadSuccess(response.body());
                }else {
                    listener.onNetworkError(activity.getString(R.string.opps_network_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsList> call, @NonNull Throwable t) {
                listener.onNetworkError(activity.getString(R.string.opps_network_error));
            }
        });
    }

    @Override
    public void showExitDialog(AlertDialog.Builder builder, OnFinishListener listener) {
        builder.setMessage("Are you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            listener.onExitPositive();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
