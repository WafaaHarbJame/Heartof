package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Adapter.HotlesAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.SoqaAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.Comments;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class HotleActivity extends BaseActivity {
    HotlesAdapter hotlesAdapter;
    private RecyclerView mRecycler;
    private DatabaseReference mFirebaseDatabase;
    private SwipeRefreshLayout mAllswip;
    private List<Hotles> hotles;
    private List<Comments> comments;

    Toolbar toolbar;
    String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة لرؤية الفنادق
        //هذة R.layout activity_hotle   الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b عند اسم layout  لرؤية التصميم
        setContentView(R.layout.activity_hotle);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        if(intent!=null) {
            city_name=intent.getStringExtra("cityname");
        }
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Hotles").child(city_name);;

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

        hotles = new ArrayList<>();


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        mRecycler.setLayoutManager(manager);

    }


    @Override
    public void onStart() {
        ConnectivityManager conMgr = (ConnectivityManager) getActiviy().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            hotles.clear();
            mAllswip.setRefreshing(true);


            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    hotles.clear();
                    for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                        Hotles hotles1 = livenapshot.getValue(Hotles.class);
                        hotles.add(hotles1);


                    }

                    hotlesAdapter = new HotlesAdapter(getActiviy(), hotles);
                    mRecycler.setAdapter(hotlesAdapter);
                    hotlesAdapter.notifyDataSetChanged();
                    mAllswip.setRefreshing(false);
                    hotlesAdapter.notifyDataSetChanged();


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