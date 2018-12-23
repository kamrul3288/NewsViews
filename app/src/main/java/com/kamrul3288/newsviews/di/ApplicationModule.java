package com.kamrul3288.newsviews.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kamrul3288.newsviews.constant.Constants;
import com.kamrul3288.newsviews.networkapi.ApiInterfaces;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {
        ContextModule.class,
        OkHttpClientModule.class
})
public class ApplicationModule {
    @Singleton
    @Provides
    public ApiInterfaces apiInterfaces(Retrofit retrofit){
        return retrofit.create(ApiInterfaces.class);
    }

    @Singleton
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("NEED TO BASE UTL") //todo
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Singleton
    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    public SharedPreferences sharedPreferences(Context context){
        return context.getSharedPreferences(Constants.SHARED_PREF_KEY,0);
    }

    @Singleton
    @Provides
    public SharedPreferences.Editor editor(SharedPreferences sharedPreferences){
        return sharedPreferences.edit();
    }
}
