package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.heartoftheworldapp.heartoftheworld.R;

public class InformationActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private TextView mTvcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        mToolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.title);
        mTvcontent = findViewById(R.id.tvcontent);
        mToolbar.setTitle(getString(R.string.information));

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.information));


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
