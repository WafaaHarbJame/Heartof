package com.heartoftheworldapp.heartoftheworld.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.heartoftheworldapp.heartoftheworld.Adapter.AzkarAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.CatogoryAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Azkar;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.Model.Offices;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class AzkarActivity extends BaseActivity {

    AzkarAdapter azkarAdapter;
    LinearLayoutManager linearLayoutManager;
    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private List<Azkar> azkars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة الاذكار
        //هذة R.layout activity_azkar   الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b عند اسم layout  لرؤية التصميم

        setContentView(R.layout.activity_azkar);
        mToolbar = findViewById(R.id.toolbar);
        mRecycler = findViewById(R.id.recycler);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.azkar));
        azkars = new ArrayList<>();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(AzkarActivity.this);
        mRecycler.setLayoutManager(manager);
        // اضافة اذكار للتطبيق باستخدام ARRAY list

        azkars.add(new Azkar(1, getString(R.string.travelazkar), getString(R.string.travlazkattext)));
        azkars.add(new Azkar(2, getString(R.string.morninandevening), getString(R.string.morninandeveningtext)));
        azkars.add(new Azkar(3, getString(R.string.mogtotrvaller), getString(R.string.mogtotrvallertext)));
        linearLayoutManager = new LinearLayoutManager(getActiviy());
        mRecycler.setLayoutManager(linearLayoutManager);
        azkarAdapter = new AzkarAdapter(getActiviy(), azkars);
        mRecycler.setAdapter(azkarAdapter);


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
