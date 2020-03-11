package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Date;

public class AddResturantActivity extends BaseActivity {

    private EditText mResturantsArName;
    private EditText mResturantsEnName;
    private EditText mResturantsArDesc;
    private EditText mResturantsEnDesc;
    private EditText mResturantsLink;
    private EditText mResturantsPhone;
    private EditText mResturantsArAddress;
    private EditText mResturantsEnAddress;
    private Button mButton;
    private DatabaseReference mFirebaseDatabase;
    int counter = 0;
    private Spinner mCityname;
    String city_name;
    SharedPManger sharedPManger;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resturant);
        mResturantsArName = findViewById(R.id.Resturants_ar_name);
        mResturantsEnName = findViewById(R.id.Resturants_en_name);
        mResturantsArDesc = findViewById(R.id.Resturants_ar_desc);
        mResturantsEnDesc = findViewById(R.id.Resturants_en__desc);
        mResturantsLink = findViewById(R.id.Resturants_link);
        mResturantsPhone = findViewById(R.id.Resturants_phone);
        mResturantsArAddress = findViewById(R.id.Resturants_ar_address);
        mResturantsEnAddress = findViewById(R.id.Resturants_en_address);
        mButton = findViewById(R.id.button);
        mCityname = findViewById(R.id.cityname);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.addresturant));
        counter = (int) new Date().getTime();

        sharedPManger=new SharedPManger(getApplicationContext());

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Resturants");
        final String id = mFirebaseDatabase.push().getKey();
        mCityname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                city_name=mCityname.getSelectedItem().toString();
                Toast(city_name);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = (int) new Date().getTime();

                counter = counter + 1;
                sharedPManger.SetData(AppConstants.counter,counter);
                if (sharedPManger.getDataInt(AppConstants.counter,0)>0) {
                    counter = sharedPManger.getDataInt(AppConstants.counter,0);
                }


                final Resturants resturants = new Resturants(counter, mResturantsArName.getText().toString(),
                        mResturantsEnName.getText().toString(), mResturantsArDesc.getText().toString(),
                        mResturantsEnDesc.getText().toString(), mResturantsLink.getText().toString(),
                        mResturantsPhone.getText().toString(), mResturantsArAddress.getText().toString(),
                        mResturantsEnAddress.getText().toString(),false,city_name);

                mFirebaseDatabase.child(city_name).child(counter + "")
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            counter=counter+1;
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(resturants);
                            mResturantsArName.setText("");
                            mResturantsEnName.setText("");
                            mResturantsArDesc.setText("");
                            mResturantsEnDesc.setText("");
                            mResturantsPhone.setText("");
                            mResturantsLink.setText("");
                            mResturantsArAddress.setText("");
                            mResturantsEnAddress.setText("");
                            Toast(getString(R.string.addingsucess));


                        }
                        else {
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(resturants);
                            mResturantsArName.setText("");
                            mResturantsEnName.setText("");
                            mResturantsArDesc.setText("");
                            mResturantsEnDesc.setText("");
                            mResturantsPhone.setText("");
                            mResturantsLink.setText("");
                            mResturantsArAddress.setText("");
                            mResturantsEnAddress.setText("");
                            Toast(getString(R.string.addingsucess));

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getActiviy(),AddingActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
