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
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Date;

public class AddSoqaaActivity extends BaseActivity {

    private Spinner mCityname;
    private EditText mSoaqArName;
    private EditText mSoaqEnName;
    private EditText mSoaqArDesc;
    private EditText mSoaqEnDesc;
    private EditText mSoaqImage;
    private Button mButton;
    private DatabaseReference mFirebaseDatabase;
    int counter = 0;
    String city_name;
    SharedPManger sharedPManger;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soqaa);
        mCityname = findViewById(R.id.cityname);
        mSoaqArName = findViewById(R.id.soaq_ar_name);
        mSoaqEnName = findViewById(R.id.soaq_en_name);
        mSoaqArDesc = findViewById(R.id.soaq__ar_desc);
        mSoaqEnDesc = findViewById(R.id.soaq__en__desc);
        mSoaqImage = findViewById(R.id.soaq_image);
        mButton = findViewById(R.id.button);
        sharedPManger=new SharedPManger(getApplicationContext());
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Alasqaa");
        final String id = mFirebaseDatabase.push().getKey();
        sharedPManger=new SharedPManger(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(getString(R.string.addsoqaa));
        counter = (int) new Date().getTime();

        mCityname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                city_name=mCityname.getSelectedItem().toString();
                //Toast(city_name);

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

                final Alsoaq alsoaq = new Alsoaq(counter,
                        mSoaqArName.getText().toString(),
                        mSoaqEnName.getText().toString(),
                        mSoaqArDesc.getText().toString(),
                        mSoaqEnDesc.getText().toString(),mSoaqImage.getText().toString(),false,city_name);

                mFirebaseDatabase.child(city_name).child(counter + "").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            counter=counter+1;
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(alsoaq);
                            mSoaqArName.setText("");
                            mSoaqEnName.setText("");
                            mSoaqArDesc.setText("");
                            mSoaqEnDesc.setText("");
                            mSoaqImage.setText("");
                            Toast(getString(R.string.addingsucess));

                        }
                        else {
                            mFirebaseDatabase.child(city_name).child(counter + "").setValue(alsoaq);
                            mSoaqArName.setText("");
                            mSoaqEnName.setText("");
                            mSoaqArDesc.setText("");
                            mSoaqEnDesc.setText("");
                            mSoaqImage.setText("");

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
