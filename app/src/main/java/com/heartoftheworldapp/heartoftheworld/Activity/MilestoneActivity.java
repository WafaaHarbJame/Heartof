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
import com.heartoftheworldapp.heartoftheworld.Adapter.MilestonesAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.SoqaAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.Milestonse;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class MilestoneActivity extends BaseActivity {
    MilestonesAdapter milestonesAdapter;
    private RecyclerView mRecycler;
    private DatabaseReference mFirebaseDatabase;
    private SwipeRefreshLayout mAllswip;
    private List<Milestonse> milestonses;
    Toolbar toolbar;
    String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milestone);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        actionBar.setTitle(getString(R.string.milestones));

        if(intent!=null) {

            city_name=intent.getStringExtra("cityname");
            Toast(city_name);

        }
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Milestones").child(city_name);;

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

        milestonses = new ArrayList<>();


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        mRecycler.setLayoutManager(manager);

    }


    @Override
    public void onStart() {
        ConnectivityManager conMgr = (ConnectivityManager) getActiviy().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            milestonses.clear();
            mAllswip.setRefreshing(true);


            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    milestonses.clear();
                    for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                        Milestonse milestonse = livenapshot.getValue(Milestonse.class);
                        milestonses.add(milestonse);


                    }

                    milestonesAdapter = new MilestonesAdapter(getActiviy(), milestonses);
                    mRecycler.setAdapter(milestonesAdapter);
                    milestonesAdapter.notifyDataSetChanged();
                    mAllswip.setRefreshing(false);
                    milestonesAdapter.notifyDataSetChanged();


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
