package com.kamrul3288.newsviews.view.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.constant.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsWebViewActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private Bundle extras;

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web_view);
        unbinder = ButterKnife.bind(this);

        extras = getIntent().getExtras();
        String url = extras.getString(Constants.NEWS_LINK);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);




    }

    @Override
    protected void onDestroy() {
        if (unbinder != null){
            unbinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
