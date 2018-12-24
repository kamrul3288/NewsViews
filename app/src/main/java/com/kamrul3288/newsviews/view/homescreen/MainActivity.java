package com.kamrul3288.newsviews.view.homescreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.adapter.NewsAdepter;
import com.kamrul3288.newsviews.model.NewsList;
import com.kamrul3288.newsviews.view.base.BaseActivity;
import com.kamrul3288.newsviews.view.homescreen.di.DaggerMainScreenComponent;
import com.kamrul3288.newsviews.view.homescreen.di.MainScreenComponent;
import com.kamrul3288.newsviews.view.homescreen.di.MainScreenModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements MainScreenContract.MainScreenView , SwipeRefreshLayout.OnRefreshListener {

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


    @Inject
    NewsAdepter adepter;

    @Inject
    MainScreenPresenterImpl mainScreenPresenter;

    @Inject
    Activity activity;

    @Inject
    GridLayoutManager gridLayoutManager;

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
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        mainScreenPresenter.loadNews();
    }

    class NavHeaderViewHolder{
        NavHeaderViewHolder(View view) {
            ButterKnife.bind(this,view);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
