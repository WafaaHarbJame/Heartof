package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Adapter.CommentAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.HotlesAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Comments;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CommentActivityActivity extends BaseActivity {
    String Hotle_link, Hotle_ar_name, Hotle_en_name, Hotle_ar_desc, Hotle_en__desc, Hotle_image, City_Name;
    int hotel_id;
    int returant_id;
    String Resturants_ar_desc;
    String Resturants_link, Resturants_ar_name, Resturants_en_name, Resturants_en__desc, Resturants_en_address, Resturants_ar_address, Resturants_phone;
    String KEY_username;
    CharSequence s;
    Comments commentsHotel;
    Comments commentsResturant;
    String commentdate;
    int counter = 0;
    int comment_type;
    CommentAdapter commentAdapter;
    private Toolbar toolbar;
    private TextView mName;
    private TextView mDesc;
    private ImageView mImage;
    private RecyclerView mRecycler;
    private EditText mEditText;
    private Button mSendcommment;
    private DatabaseReference mFirebaseCommentsDatabase;
    private List<Comments> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // شاشة لرؤية التعليقات وانشاء تعليق
        //هذة R.layout activity_comment_activity   الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b عند اسم layout  لرؤية التصميم

        setContentView(R.layout.activity_comment_activity);
        toolbar = findViewById(R.id.toolbar);
        mName = findViewById(R.id.name);
        mDesc = findViewById(R.id.desc);
        mImage = findViewById(R.id.image);
        mRecycler = findViewById(R.id.recycler);
        mEditText = findViewById(R.id.editText);
        mSendcommment = findViewById(R.id.sendcommment);
        setSupportActionBar(toolbar);
        sharedPManger = new SharedPManger(getActiviy());
        comments = new ArrayList<>();
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        mFirebaseCommentsDatabase = FirebaseDatabase.getInstance().getReference("Comments");
        KEY_username = sharedPManger.getDataString(AppConstants.KEY_username);

        commentdate = formatDate(Calendar.getInstance().getTime());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        mRecycler.setLayoutManager(manager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        commentAdapter = new CommentAdapter(getActiviy(), comments);

        Hotle_ar_name = getIntent().getStringExtra("Hotle_ar_name");
        Hotle_en_name = getIntent().getStringExtra("Hotle_en_name");
        Hotle_ar_desc = getIntent().getStringExtra("Hotle_ar_desc");
        Hotle_en__desc = getIntent().getStringExtra("Hotle_en__desc");
        Hotle_link = getIntent().getStringExtra("Hotle_link");
        Hotle_image = getIntent().getStringExtra("Hotle_image");
        hotel_id = getIntent().getIntExtra("hotel_id", 0);
        Hotle_en__desc = getIntent().getStringExtra("Hotle_en__desc");
        City_Name = getIntent().getStringExtra("City_Name");
        Hotle_image = getIntent().getStringExtra("Hotle_image");

        Log.d("wafaa", "hotel_id recieve" + hotel_id);
        comment_type = getIntent().getIntExtra("comment_type", 0);
        Resturants_ar_name = getIntent().getStringExtra("Resturants_ar_name");
        Resturants_en_name = getIntent().getStringExtra("Resturants_en_name");
        Resturants_link = getIntent().getStringExtra("Resturants_link");
        Resturants_ar_desc = getIntent().getStringExtra("Resturants_ar_desc");
        Resturants_en__desc = getIntent().getStringExtra("Resturants_en__desc");
        returant_id = getIntent().getIntExtra("returant_id", 0);
        Resturants_en__desc = getIntent().getStringExtra("Resturants_en__desc");
        City_Name = getIntent().getStringExtra("City_Name");
        Resturants_en_address = getIntent().getStringExtra("Resturants_en_address");
        Resturants_ar_address = getIntent().getStringExtra("Resturants_ar_address");
        Resturants_phone = getIntent().getStringExtra("Resturants_phone");

        if (comment_type == 0) {
            if(appLanguage.matches("ar")){
                mName.setText(Hotle_ar_name);
                mDesc.setText(Hotle_ar_desc);
                Picasso.with(context).load(Hotle_image)
                        .error(R.drawable.logoo)
                        .placeholder(R.drawable.raidaa)
                        .into(mImage);


            }

            else {

                mName.setText(Hotle_en_name);
                mDesc.setText(Hotle_en__desc);
                Picasso.with(context).load(Hotle_image)
                        .error(R.drawable.logoo)
                        .placeholder(R.drawable.raidaa)
                        .into(mImage);

            }


        } else if (comment_type == 1) {
            if(appLanguage.matches("ar")){
                mName.setText(Resturants_ar_name);
                mDesc.setText(Resturants_ar_desc);

            }

            else {

                mName.setText(Resturants_en_name);
                mDesc.setText(Resturants_en__desc);


            }


        }

        mSendcommment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = counter + 1;
                sharedPManger.SetData(AppConstants.counter, counter);
                if (sharedPManger.getDataInt(AppConstants.counter, 0) > 0) {
                    counter = sharedPManger.getDataInt(AppConstants.counter, 0);


                }

                if (mEditText.getText().toString().equals(null) || mEditText.getText().toString().equals("")) {
                    mEditText.setError(getString(R.string.comment_textrequied));
                    mEditText.requestFocus();

                } else if (comment_type == 0) {
                    commentdate = formatDate(Calendar.getInstance().getTime());
                    commentsHotel = new Comments(hotel_id, mEditText.getText().toString(), commentdate, KEY_username);

                    mFirebaseCommentsDatabase.child("HotelComments").child(hotel_id + "").child(counter + "").
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        mFirebaseCommentsDatabase.child("HotelComments").child(hotel_id + "").child(counter + 1 + "").setValue(commentsHotel);
                                    } else {
                                        mFirebaseCommentsDatabase.child("HotelComments").child(hotel_id + "").child(counter + "").setValue(commentsHotel);


                                    }
                                    Toast(getString(R.string.addingsucess));
                                    mEditText.setText("");

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                } else if (comment_type == 1) {
                    commentdate = formatDate(Calendar.getInstance().getTime());

                    commentsResturant = new Comments(returant_id, mEditText.getText().toString(), commentdate, KEY_username);

                    mFirebaseCommentsDatabase.child("ResturantComments").child(returant_id + "").child(counter + "").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                mFirebaseCommentsDatabase.child("ResturantComments").child(returant_id + "").child(counter + 1 + "").setValue(commentsResturant);
                            } else {
                                mFirebaseCommentsDatabase.child("ResturantComments").child(returant_id + "").child(counter + "").setValue(commentsResturant);

                            }
                            Toast(getString(R.string.addingsucess));
                            mEditText.setText("");

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            }
        });


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


    public String formatDate(Date date) {
        // Locale locale = new Locale( "ar" , "SA" ) ;  // Arabic language. Saudi Arabia cultural norms.
        SimpleDateFormat customFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        customFormat.setLenient(false);
        customFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return customFormat.format(date);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (comment_type == 0) {
            mFirebaseCommentsDatabase.child("HotelComments").child(hotel_id + "").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    comments.clear();
                    for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                        Comments comments1 = livenapshot.getValue(Comments.class);
                        comments.add(comments1);


                    }

                    commentAdapter = new CommentAdapter(getActiviy(), comments);
                    mRecycler.setAdapter(commentAdapter);
                    commentAdapter.notifyDataSetChanged();
                    commentAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else if (comment_type == 1) {
            commentdate = formatDate(Calendar.getInstance().getTime());

            commentsResturant = new Comments(returant_id, mEditText.getText().toString(), commentdate, KEY_username);

            mFirebaseCommentsDatabase.child("ResturantComments").
                    child(returant_id + "").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    comments.clear();
                    for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                        Comments comments1 = livenapshot.getValue(Comments.class);
                        comments.add(comments1);


                    }

                    commentAdapter = new CommentAdapter(getActiviy(), comments);
                    mRecycler.setAdapter(commentAdapter);
                    commentAdapter.notifyDataSetChanged();
                    commentAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }
}

