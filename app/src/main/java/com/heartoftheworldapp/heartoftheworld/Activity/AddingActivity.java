package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.heartoftheworldapp.heartoftheworld.R;

public class AddingActivity extends BaseActivity {

    private TextView mAdding;
    private Button mAddResturant;
    private Button mAddHotle;
    private Button mAddMilestone;
    private Button mAddSoqaa;
    private Button mAddoffice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        mAdding = findViewById(R.id.adding);
        mAddResturant = findViewById(R.id.AddResturant);
        mAddHotle = findViewById(R.id.AddHotle);
        mAddMilestone = findViewById(R.id.AddMilestone);
        mAddSoqaa = findViewById(R.id.AddSoqaa);
        mAddoffice = findViewById(R.id.addoffice);


        mAddHotle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActiviy(), AddHotleActivity.class);
                startActivity(intent);
            }
        });
        mAddResturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActiviy(), AddResturantActivity.class);
                startActivity(intent);
            }
        });
        mAddMilestone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActiviy(), AddMilestonesActivity.class);
                startActivity(intent);
            }
        });
        mAddSoqaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActiviy(), AddSoqaaActivity.class);
                startActivity(intent);
            }
        });

        mAddoffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActiviy(), addTouristOffice.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActiviy(), LoginActivity.class);
        startActivity(intent);
    }
}
