package com.kamrul3288.newsviews.view.numberapiscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.constant.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NumberApiInfoActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @BindView(R.id.resultText)
    TextView resultText;

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_api_info);
        unbinder = ButterKnife.bind(this);

        setToolBar();

        extras = getIntent().getExtras();
        resultText.setText(extras.getString(Constants.NUMBER_API_RESULT));
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Search Result");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null){
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
