package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Adapter.CommentAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Comments;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MilestoneDetailsActivity extends BaseActivity {
    String  City_Name;
    String KEY_username;
    String Milestonse__en__desc,Milestonse_image,Milestonse__ar_desc;
    int Milestonse_id;
    Comments commentsHotel;
    Comments commentsResturant;
    private Toolbar toolbar;
    private TextView mName;
    private TextView mDesc;
    private ImageView mImage;
    private RecyclerView mRecycler;
    private EditText mEditText;
    private Button mSendcommment;
    private DatabaseReference mFirebaseCommentsDatabase;
    String commentdate;
    int counter = 0;
    int comment_type;
    private List<Comments> comments;
    CommentAdapter commentAdapter;
    boolean ISLOGIN;
    SharedPManger sharedPManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة لعرض تفاصيل المعلم
        //هذة R.layout activity_milestone_details  الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b لرؤية التصميم
        setContentView(R.layout.activity_milestone_details);
        toolbar = findViewById(R.id.toolbar);
        mName = findViewById(R.id.name);
        mDesc = findViewById(R.id.desc);
        mImage = findViewById(R.id.image);
        mRecycler = findViewById(R.id.recycler);
        mEditText = findViewById(R.id.editText);
        mSendcommment = findViewById(R.id.sendcommment);
        setSupportActionBar(toolbar);
        sharedPManger = new SharedPManger(getActiviy());
        comments=new ArrayList<>();
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        mFirebaseCommentsDatabase = FirebaseDatabase.getInstance().getReference("Comments");
        KEY_username = sharedPManger.getDataString(AppConstants.KEY_username);
        ISLOGIN = sharedPManger.getDataBool(AppConstants.ISLOGIN, false);

        commentdate = formatDate(Calendar.getInstance().getTime());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        mRecycler.setLayoutManager(manager);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        commentAdapter=new CommentAdapter(getActiviy(),comments);
        City_Name = getIntent().getStringExtra("City_Name");
        comment_type = getIntent().getIntExtra("comment_type", 0);
        Milestonse__en__desc = getIntent().getStringExtra("Milestonse__en__desc");
        Milestonse_id = getIntent().getIntExtra("Milestonse_id", 0);
        Milestonse_image = getIntent().getStringExtra("Milestonse_image");
        City_Name = getIntent().getStringExtra("City_Name");
        Milestonse__ar_desc = getIntent().getStringExtra("Milestonse__ar_desc");

        if(appLanguage.matches("ar")){
            mDesc.setText(Milestonse__ar_desc);

        }
        else {
            mDesc.setText(Milestonse__en__desc);


        }
        Picasso.with(context).load(Milestonse_image)
                .error(R.drawable.logoo)
                .placeholder(R.drawable.raidaa)
                .into(mImage);



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

                } else if (comment_type == 2) {
                    commentdate = formatDate(Calendar.getInstance().getTime());
                    commentsHotel = new Comments(Milestonse_id, mEditText.getText().toString(), commentdate, KEY_username);

                    mFirebaseCommentsDatabase.
                            child("MilestoneComments").child(Milestonse_id + "").child(counter + "").
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                   mFirebaseCommentsDatabase.child("MilestoneComments").child(Milestonse_id + "")
                                           .child(counter + 1 + "").setValue(commentsHotel);
                                    } else {
                                        mFirebaseCommentsDatabase.child("MilestoneComments").child(Milestonse_id + "")
                                                .child(counter + "").setValue(commentsHotel);


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

        if (comment_type == 2) {
            mFirebaseCommentsDatabase.child("MilestoneComments").child(Milestonse_id + "").addValueEventListener(new ValueEventListener() {
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
