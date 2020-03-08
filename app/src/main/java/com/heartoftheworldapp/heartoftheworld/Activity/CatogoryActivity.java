package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.heartoftheworldapp.heartoftheworld.Adapter.CatogoryAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class CatogoryActivity extends BaseActivity {
    RecyclerView recyclerView;
    List<Catogory> catogories;
    CatogoryAdapter catogoryAdapter;
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView_cat;
    GridLayoutManager gridLayoutManager;
    Toolbar toolbar;
    String name="";
    String city_arname;
    SharedPManger sharedPManger;
    String appLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catogory);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView_cat = findViewById(R.id.recycler);
        gridLayoutManager=new GridLayoutManager(getActiviy(), 2);
        catogories = new ArrayList<>();
        Intent intent=getIntent();
        sharedPManger = new SharedPManger(getActiviy());
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if(intent!=null) {
            int id = intent.getIntExtra("id", 1);
             name=intent.getStringExtra("cityname");
            city_arname=intent.getStringExtra("cityarname");
            if (appLanguage.matches("ar")) {
                actionBar.setTitle(city_arname);

            }
            else {
                actionBar.setTitle(name);

            }

            catogories.add(new Catogory(1,getString(R.string.Restaurants), "https://www.ahstatic.com/photos/5394_rsr001_00_p_1024x768.jpg",name));
            catogories.add(new Catogory(2,getString(R.string.hotels), "https://ihg.scene7.com/is/image/ihg/even-hotels-eugene-5405616297-4x3",name));
            catogories.add(new Catogory(3,getString(R.string.milestones), "https://albenaa-wpengine.netdna-ssl.com/wp-content/uploads/2014/04/13.jpg",name));
            catogories.add(new Catogory(4,getString(R.string.caroffices), "https://previews.123rf.com/images/graphicbee/graphicbee1705/graphicbee170500043/77464389-yellow-taxi-car-and-taxi-driver.jpg",name));
            catogories.add(new Catogory(5,getString(R.string.aboutcity), "https://assetsds.cdnedge.bluemix.net/sites/default/files/styles/big_2/public/feature/images/combatting_bank_loan.jpg?itok=bAH7OB_o",name));
            catogories.add(new Catogory(6,getString(R.string.ALASQA), "https://cdn.openpr.com/R/9/R91489600_g.jpg",name));
            recyclerView_cat.setLayoutManager(gridLayoutManager);
            catogoryAdapter = new CatogoryAdapter(getActiviy(), catogories);
            recyclerView_cat.setAdapter(catogoryAdapter);


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
