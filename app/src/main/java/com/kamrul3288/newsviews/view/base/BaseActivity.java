package com.kamrul3288.newsviews.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kamrul3288.newsviews.NewsApplication;
import com.kamrul3288.newsviews.di.ApplicationComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public ApplicationComponent getApplicationComponents(){
        return ((NewsApplication) getApplication()).getApplicationComponent();
    }
}
