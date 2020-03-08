package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Adapter.OfficeAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.ResturantAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Offices;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class OfficeActivity extends BaseActivity {
    OfficeAdapter officeAdapter;
    private RecyclerView mRecycler;
    private DatabaseReference mFirebaseDatabase;
    private SwipeRefreshLayout mAllswip;
    private List<Offices> offices;
    Toolbar toolbar;
    String city_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        actionBar.setTitle(getString(R.string.caroffices));

        if(intent!=null) {

            city_name=intent.getStringExtra("cityname");
            Toast(city_name);

        }
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Offices").child(city_name);;

        mRecycler = findViewById(R.id.recycler);
        mAllswip = findViewById(R.id.allswip);
        mAllswip.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);

        mAllswip.setRefreshing(false);

        mAllswip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                onStart();


            }
        });

        offices = new ArrayList<>();


        RecyclerView.LayoutManager manager = new LinearLayoutManager(OfficeActivity.this);
        mRecycler.setLayoutManager(manager);

    }


    @Override
    public void onStart() {
        ConnectivityManager conMgr = (ConnectivityManager) OfficeActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            offices.clear();
            mAllswip.setRefreshing(true);


            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    offices.clear();
                    for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                        Offices offices1 = livenapshot.getValue(Offices.class);
                        offices.add(offices1);


                    }

                    officeAdapter = new OfficeAdapter(OfficeActivity.this, offices);
                    mRecycler.setAdapter(officeAdapter);
                    officeAdapter.notifyDataSetChanged();
                    mAllswip.setRefreshing(false);
                    officeAdapter.notifyDataSetChanged();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });
        } else {

            mAllswip.setRefreshing(false);
        }
        super.onStart();
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