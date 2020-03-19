package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.heartoftheworldapp.heartoftheworld.R;

public class WeatherActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private TextView mTvcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة لعرض المناخ الخاص بالمملكة
        //هذة R.layout activity_weather  الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b لرؤية التصميم
        setContentView(R.layout.activity_weather);
        mToolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.title);
        mTvcontent = findViewById(R.id.tvcontent);
        mToolbar.setTitle(getString(R.string.climate));

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
