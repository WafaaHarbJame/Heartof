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
import com.heartoftheworldapp.heartoftheworld.Model.Offices;
import com.heartoftheworldapp.heartoftheworld.R;

public class addTouristOffice extends BaseActivity {

    int counter = 0;
    String city_name;
    Toolbar toolbar;
    private EditText mOfficetype;
    private EditText mOfficearname;
    private EditText mOfficeenname;
    private EditText mOfficenumber;
    private Button mAddoffice;
    private DatabaseReference mFirebaseDatabase;
    private EditText mOfficetypear;
    private EditText mOfficetypeen;
    private Spinner mCityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tourist_office);
        mOfficearname = findViewById(R.id.officearname);
        mOfficeenname = findViewById(R.id.officeenname);
        mOfficenumber = findViewById(R.id.officenumber);
        mAddoffice = findViewById(R.id.addoffice);
        mOfficetypear = findViewById(R.id.officetypear);
        mOfficetypeen = findViewById(R.id.officetypeen);
        mCityname = findViewById(R.id.cityname);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.addoffice));
        actionBar.setTitle(getString(R.string.addoffice));

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Offices");
        mCityname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                city_name = mCityname.getSelectedItem().toString();
                Toast(city_name);

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mAddoffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                counter = counter + 1;
                sharedPManger.SetData(AppConstants.counter, counter);
                if (sharedPManger.getDataInt(AppConstants.counter, 0) > 0) {
                    counter = sharedPManger.getDataInt(AppConstants.counter, 0);
                }

                final Offices offices = new Offices(counter, mOfficetypear.getText().toString(), mOfficetypeen.getText().toString(), mOfficearname.getText().toString(), mOfficeenname.getText().toString(), mOfficenumber.getText().toString());

                mFirebaseDatabase.child(city_name).child(counter + "").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            counter = counter + 1;
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(offices);
                            mOfficetypear.setText("");
                            mOfficetypeen.setText("");
                            mOfficearname.setText("");
                            mOfficeenname.setText("");
                            mOfficenumber.setText("");
                            Toast(getString(R.string.addingsucess));

                        } else {
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(offices);
                            mOfficetypear.setText("");
                            mOfficetypeen.setText("");
                            mOfficearname.setText("");
                            mOfficeenname.setText("");
                            mOfficenumber.setText("");
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
                Intent intent = new Intent(getActiviy(), AddingActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
