package com.kamrul3288.newsviews.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamrul3288.newsviews.R;

public class WelcomeScreenPage3 extends Fragment {


    public static WelcomeScreenPage3 getInstance() {
        return new WelcomeScreenPage3();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome_screen_page3, container, false);
    }

}
