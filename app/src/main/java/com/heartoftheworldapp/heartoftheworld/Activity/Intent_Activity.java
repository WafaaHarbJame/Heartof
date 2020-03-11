package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.heartoftheworldapp.heartoftheworld.R;

public class Intent_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_);
        int hotel_id=getIntent().getIntExtra("hotel_id",0);
        Log.d("wafaa","hotel_id recieve"+hotel_id);
        String Hotle_ar_name=getIntent().getStringExtra("Hotle_en_name");
        String Hotle_ar_desc=getIntent().getStringExtra("Hotle_ar_desc");
        String Hotle_en__desc=getIntent().getStringExtra("Hotle_en__desc");




    }
}
