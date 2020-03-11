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
import com.heartoftheworldapp.heartoftheworld.Adapter.ResturantAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class ResturantActivity extends BaseActivity {

    private RecyclerView mRecycler;
    private DatabaseReference mFirebaseDatabase;
    private SwipeRefreshLayout mAllswip;
    private  List<Resturants> resturants;
    Toolbar toolbar;
    String city_name;
    ResturantAdapter resturantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(getString(R.string.Restaurants));

        mRecycler = findViewById(R.id.recycler);
        mAllswip = findViewById(R.id.allswip);

        Intent intent=getIntent();
        if(intent!=null) {

            city_name=intent.getStringExtra("cityname");

        }

            mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Resturants").child(city_name);
        mAllswip.setColorSchemeResources
                (R.color.colorPrimary, android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark,
                        android.R.color.holo_blue_dark);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(ResturantActivity.this);
        resturants = new ArrayList<>();

        mRecycler.setLayoutManager(manager);
        mAllswip.setRefreshing(false);

        mAllswip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                onStart();


            }
        });
    }




    @Override
    public void onStart() {
        ConnectivityManager conMgr = (ConnectivityManager)ResturantActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            resturants.clear();
            mAllswip.setRefreshing(true);


            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    resturants.clear();
                    for(DataSnapshot livenapshot:dataSnapshot.getChildren()) {
                        Resturants request = livenapshot.getValue(Resturants.class);
                        resturants.add(request);

                    }

                    resturantAdapter = new ResturantAdapter( ResturantActivity.this,resturants);
                    mRecycler.setAdapter(resturantAdapter);
                    resturantAdapter.notifyDataSetChanged();
                    mAllswip.setRefreshing(false);
                    resturantAdapter.notifyDataSetChanged();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });}
        else {

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
