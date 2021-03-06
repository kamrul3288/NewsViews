package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.adapter.NewsAdepter;
import com.kamrul3288.newsviews.constant.Constants;
import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.view.aboutscreen.AboutActivity;
import com.kamrul3288.newsviews.view.base.BaseActivity;
import com.kamrul3288.newsviews.view.homescreen.di.DaggerMainScreenComponent;
import com.kamrul3288.newsviews.view.homescreen.di.MainScreenComponent;
import com.kamrul3288.newsviews.view.homescreen.di.MainScreenModule;
import com.kamrul3288.newsviews.view.numberapiscreen.NumberApiInfoActivity;
import com.kamrul3288.newsviews.view.socialloginscreen.SocialLoginActivity;
import com.miguelcatalan.materialsearchview.MaterialSearchView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements MainScreenContract.MainScreenView ,
        SwipeRefreshLayout.OnRefreshListener, MaterialSearchView.OnQueryTextListener {

    private Unbinder unbinder;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.networkErrorText)
    TextView networkErrorText;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;


    @Inject
    NewsAdepter adepter;
    @Inject
    MainScreenPresenterImpl mainScreenPresenter;
    @Inject
    Activity activity;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        MainScreenComponent component = DaggerMainScreenComponent.builder()
                .applicationComponent(getApplicationComponents())
                .mainScreenModule(new MainScreenModule(this,this))
                .build();
        component.inject(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        setToolBar();
        setNavigationDrawer();
        new NavHeaderViewHolder(navigationView.getHeaderView(0));
        mainScreenPresenter.loadNews();
        searchView.setOnQueryTextListener(this);





    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Home");
        }
    }

    private void setNavigationDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        searchView.setHint("number ex:11 and date ex: 12/10");
        searchView.setHintTextColor(Color.parseColor("#AEAEAE"));
        return true;
    }

    @Override
    public void showProgressBar() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressBar() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNews(NewsList newsList) {
        swipeRefreshLayout.setRefreshing(false);
        networkErrorText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adepter);
        adepter.setItem(newsList);
    }

    @Override
    public void showNetworkError(String message) {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        networkErrorText.setVisibility(View.VISIBLE);
        networkErrorText.setText(message);
    }

    @Override
    public void exitFromApp() {
        finish();
    }

    @Override
    public void showNumberAndDateResultInfo(String result) {
        startActivity(new Intent(activity,NumberApiInfoActivity.class).putExtra(Constants.NUMBER_API_RESULT,result));
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mainScreenPresenter.loadNews();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mainScreenPresenter.loadNumberApi(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    class NavHeaderViewHolder{
        NavHeaderViewHolder(View view) {
            ButterKnife.bind(this,view);
        }

        @OnClick(R.id.ll_home)
        public void home(){
            drawer.closeDrawers();
        }

        @OnClick(R.id.ll_socialLogin)
        public void gotoSocialLoginPage(){
            drawer.closeDrawers();
            startActivity(new Intent(activity,SocialLoginActivity.class));
        }

        @OnClick(R.id.ll_about)
        public void gotoAboutPage(){
            drawer.closeDrawers();
            startActivity(new Intent(activity,AboutActivity.class));
        }

        @OnClick(R.id.ll_exit)
        public void exit(){
            drawer.closeDrawers();
            mainScreenPresenter.showExitDialog();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (searchView.isSearchOpen()) {
                searchView.closeSearch();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null){
            unbinder.unbind();
        }
        mainScreenPresenter.onDestroy();
        super.onDestroy();
    }






}
