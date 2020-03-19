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
import com.heartoftheworldapp.heartoftheworld.Adapter.SoqaAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.Offices;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class SoqaaActivity extends BaseActivity {
    SoqaAdapter soqaAdapter;
    private RecyclerView mRecycler;
    private DatabaseReference mFirebaseDatabase;
    private SwipeRefreshLayout mAllswip;
    private List<Alsoaq> alsoaqs;
    Toolbar toolbar;
    String city_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة لعرض الاسواق
        //هذة R.layout activity_soqaa  الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b لرؤية التصميم

        setContentView(R.layout.activity_soqaa);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        if(intent!=null) {

            city_name=intent.getStringExtra("cityname");

        }
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Alasqaa").child(city_name);;

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

        alsoaqs = new ArrayList<>();


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        mRecycler.setLayoutManager(manager);

    }


    @Override
    public void onStart() {
        ConnectivityManager conMgr = (ConnectivityManager) getActiviy().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            alsoaqs.clear();
            mAllswip.setRefreshing(true);


            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    alsoaqs.clear();
                    for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                        Alsoaq alsoaq = livenapshot.getValue(Alsoaq.class);
                        alsoaqs.add(alsoaq);


                    }

                    soqaAdapter = new SoqaAdapter(getActiviy(), alsoaqs);
                    mRecycler.setAdapter(soqaAdapter);
                    soqaAdapter.notifyDataSetChanged();
                    mAllswip.setRefreshing(false);
                    soqaAdapter.notifyDataSetChanged();


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