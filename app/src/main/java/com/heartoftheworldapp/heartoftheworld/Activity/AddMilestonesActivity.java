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
import com.heartoftheworldapp.heartoftheworld.Model.Milestonse;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Date;

public class AddMilestonesActivity extends BaseActivity {

    private Spinner mCityname;
    private EditText mMilestonseArDesc;
    private EditText mMilestonseEnDesc;
    private EditText mMilestonseImage;
    private Button mButton;
    private DatabaseReference mFirebaseDatabase;
    int counter = 0;
    String city_name;
    SharedPManger sharedPManger;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة اضافة معلم
        //هذة R.layout.activity_add_milestones  الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b لرؤية التصميم
        setContentView(R.layout.activity_add_milestones);
        mCityname = findViewById(R.id.cityname);
        mMilestonseArDesc = findViewById(R.id.Milestonse__ar_desc);
        mMilestonseEnDesc = findViewById(R.id.Milestonse__en__desc);
        mMilestonseImage = findViewById(R.id.Milestonse_image);
        mButton = findViewById(R.id.button);
        // تعريف جدول Milestones في الفابيرس لتخزين المعالم الخاص بكل مدينة

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Milestones");
        final String id = mFirebaseDatabase.push().getKey();
        counter = (int) new Date().getTime();

        sharedPManger=new SharedPManger(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
       toolbar.setTitle(getString(R.string.addmilestone));


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
//بدء عملية حفظ البيانات وفحصها اذا كانت موجودة بالفابيرس اولا

                final Milestonse milestonse = new Milestonse(counter,
                        mMilestonseArDesc.getText().toString(), mMilestonseEnDesc.getText().toString(),
                        mMilestonseImage.getText().toString(),false,city_name);
                mFirebaseDatabase.child(city_name).child(counter + "").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            counter=counter+1;
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(milestonse);
                            mMilestonseArDesc.setText("");
                            mMilestonseEnDesc.setText("");
                            mMilestonseImage.setText("");
                            mMilestonseImage.setText("");
                            Toast(getString(R.string.addingsucess));
                        }
                        else {
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(milestonse);
                            mMilestonseArDesc.setText("");
                            mMilestonseEnDesc.setText("");
                            mMilestonseImage.setText("");
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
