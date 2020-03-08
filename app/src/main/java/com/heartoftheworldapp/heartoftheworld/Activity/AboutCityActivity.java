package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.heartoftheworldapp.heartoftheworld.R;

public class AboutCityActivity extends BaseActivity {
    Toolbar toolbar;
    String city_name;
    private TextView mTitle;
    private TextView mTvcontent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_city);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.aboutcity));
        Intent intent = getIntent();
        if (intent != null) {
            city_name = intent.getStringExtra("cityname");
        }

        mTitle = findViewById(R.id.title);
        mTvcontent = findViewById(R.id.tvcontent);
        if(city_name.matches("AlHasa")){
            mTitle.setText(getString(R.string.AlHasa));
            mTvcontent.setText(getString(R.string.AlHasaabout));

        }

        if(city_name.matches("AlBaha")){
            mTitle.setText(getString(R.string.AlBaha));
            mTvcontent.setText(getString(R.string.AlBahaabout));

        }

        if(city_name.matches("Abha")){
            mTitle.setText(getString(R.string.Abha));
            mTvcontent.setText(getString(R.string.Abhaabout));

        }

        if(city_name.matches("Jazan")){
            mTitle.setText(getString(R.string.Jazan));
            mTvcontent.setText(getString(R.string.Jazanabout));

        } if(city_name.matches("Makkah")){
            mTitle.setText(getString(R.string.Makkah));
            mTvcontent.setText(getString(R.string.Makkahabout));

        } if(city_name.matches("Medina")){
            mTitle.setText(getString(R.string.Medina));
            mTvcontent.setText(getString(R.string.Medinaabout));

        } if(city_name.matches("Dammam")){
            mTitle.setText(getString(R.string.Dammam));
            mTvcontent.setText(getString(R.string.DamaaAbout));

        } if(city_name.matches("AlKhober")){
            mTitle.setText(getString(R.string.AlKhober));
            mTvcontent.setText(getString(R.string.AlKhoberabout));

        }

        if(city_name.matches("Riyadh")){
            mTitle.setText(getString(R.string.Riyadh));
            mTvcontent.setText(getString(R.string.AlKhoberabout));

        }

        if(city_name.matches("Hail")){
            mTitle.setText(getString(R.string.Hail));
            mTvcontent.setText(getString(R.string.Hailabout));

        }

        if(city_name.matches("Jeddah")){
            mTitle.setText(getString(R.string.Jeddah));
            mTvcontent.setText(getString(R.string.Jeddahabout));

        }
        if(city_name.matches("Tabuk")){
            mTitle.setText(getString(R.string.Tabuk));
            mTvcontent.setText(getString(R.string.Tabukabout));

        }



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
