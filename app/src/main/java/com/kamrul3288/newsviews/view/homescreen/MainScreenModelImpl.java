package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.constant.Constants;
import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenModelImpl implements MainScreenContract.MainScreenModel{

    @Override
    public void loadNews(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity) {

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
        df.setTimeZone(TimeZone.getTimeZone("gmt"));
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



    @Override
    public void loadNumberInfo(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity,String number) {
        Call<String> call = apiInterfaces.getNumberInfo(number);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()){
                    listener.onNumberAndDateResultInfo(response.body());
                }else {
                    Toast.makeText(activity,"Not found! Please enter valid number",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(activity,activity.getString(R.string.opps_network_error),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void loadDateInfo(OnFinishListener listener, ApiInterfaces apiInterfaces, Activity activity,String query) {
        Call<String> call = apiInterfaces.getDateInfo(query);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()){
                    listener.onNumberAndDateResultInfo(response.body());
                }else {
                    Toast.makeText(activity,"Not found! Please enter valid date",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(activity,activity.getString(R.string.opps_network_error),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
