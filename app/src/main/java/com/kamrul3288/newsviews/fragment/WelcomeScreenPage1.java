package com.kamrul3288.newsviews.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamrul3288.newsviews.R;


public class WelcomeScreenPage1 extends Fragment {


    public static WelcomeScreenPage1 getInstnace() {
        return new WelcomeScreenPage1();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_screen_page1, container, false);
    }

}
