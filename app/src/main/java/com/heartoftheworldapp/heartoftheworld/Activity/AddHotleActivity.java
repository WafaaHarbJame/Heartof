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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Date;

public class AddHotleActivity extends BaseActivity {

    private Spinner mCityname;
    private EditText mHotleArName;
    private EditText mHotleEnName;
    private EditText mHotleArDesc;
    private EditText mHotleEnDesc;
    private EditText mHotleLink;
    private EditText mHotlePrice;
    private EditText mHotleImage;
    private Button mButton;
    private DatabaseReference mFirebaseDatabase;
    int counter = 0;
    String city_name;
    Toolbar toolbar;
   // int  transActionID;
    SharedPManger sharedPManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة لاضافة فندق
        //هذة R.layout activity_add_hotle  الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b لرؤية التصميم

        setContentView(R.layout.activity_add_hotle);
        mCityname = findViewById(R.id.cityname);
        mHotleArName = findViewById(R.id.Hotle_ar_name);
        mHotleEnName = findViewById(R.id.Hotle_en_name);
        mHotleArDesc = findViewById(R.id.Hotle_ar_desc);
        mHotleEnDesc = findViewById(R.id.Hotle_en__desc);
        mHotleLink = findViewById(R.id.Hotle_link);
        mHotlePrice = findViewById(R.id.Hotle_price);
        mHotleImage = findViewById(R.id.Hotle_image);
        mButton = findViewById(R.id.button);
        sharedPManger=new SharedPManger(getActiviy());
        //اانشاء جدول  Hotles لتخزين بيانات الفنادق
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Hotles");
        final String id = mFirebaseDatabase.push().getKey();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.addhotle));
        actionBar.setTitle(getString(R.string.addhotle));
        //transActionID = (int) System.currentTimeMillis();
         counter = (int) new Date().getTime();
        mCityname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                city_name=mCityname.getSelectedItem().toString();
                Toast(city_name);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


// اضافة الفندق المدخل في فاعدة البيانات في جدولHotles
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                counter = (int) new Date().getTime();

                counter = counter + 1;
                sharedPManger.SetData(AppConstants.counter,counter);
                if (sharedPManger.getDataInt(AppConstants.counter,0)>0) {
                    counter = sharedPManger.getDataInt(AppConstants.counter,0);


                }
                final Hotles hotles = new Hotles(counter,
                        mHotleArName.getText().toString(), mHotleEnName.getText().toString(),
                        mHotleArDesc.getText().toString(), mHotleEnDesc.getText().toString(),
                        mHotleLink.getText().toString(), mHotlePrice.getText().toString(),
                        mHotleImage.getText().toString(),false,city_name);


                mFirebaseDatabase.child(city_name).child(counter + "").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            counter=counter+1;
                            mFirebaseDatabase.child(city_name).child(counter+"").setValue(hotles);
                            mHotleArName.setText("");
                            mHotleEnName.setText("");
                            mHotleArDesc.setText("");
                            mHotleEnDesc.setText("");
                            mHotleLink.setText("");
                            mHotlePrice.setText("");
                            mHotleImage.setText("");

                            Toast(getString(R.string.addingsucess));
                        }

                        else {
                            mFirebaseDatabase.child(city_name).child(counter+"").setValue(hotles);
                            mHotleArName.setText("");
                            mHotleEnName.setText("");
                            mHotleArDesc.setText("");
                            mHotleEnDesc.setText("");
                            mHotleLink.setText("");
                            mHotlePrice.setText("");
                            mHotleImage.setText("");
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
