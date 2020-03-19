package com.heartoftheworldapp.heartoftheworld.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class LocationActivity extends BaseActivity {

    SharedPManger sharedPManger;
    String appLanguage;
    Boolean isFavorite=false;
    boolean ISLOGIN;
    String KEY_PHONE;
    int returant_id;
    String Resturants_ar_desc;
    String Resturants_link, Resturants_ar_name, Resturants_en_name,
             Resturants_en__desc, City_Name, Resturants_en_address, Resturants_ar_address, Resturants_phone;
    private Toolbar toolbar;
    private WebView mwebView;
    private DatabaseReference mFirebaseFavDatabase;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة لرؤية الموقع على الخريطة
        //هذة R.layout activity_location   الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b عند اسم layout  لرؤية التصميم

        setContentView(R.layout.activity_location);
        mwebView = findViewById(R.id.Webview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPManger = new SharedPManger(getActiviy());
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        mFirebaseFavDatabase = FirebaseDatabase.getInstance().getReference("Favorites");
        ;

        ISLOGIN = sharedPManger.getDataBool(AppConstants.ISLOGIN, false);
        KEY_PHONE = sharedPManger.getDataString(AppConstants.KEY_PHONE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        WebSettings webSettings = mwebView.getSettings();
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setJavaScriptEnabled(true);
        mwebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mwebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mwebView.getSettings().setAppCacheEnabled(true);
        mwebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);

        Intent intent = getIntent();
        mwebView.setWebViewClient(new MyWebviewClient());
        mwebView.clearCache(true);

        if (intent != null) {
             Resturants_ar_name = intent.getStringExtra("Resturants_ar_name");
             Resturants_en_name = intent.getStringExtra("Resturants_en_name");
             Resturants_link = intent.getStringExtra("Resturants_link");
             Resturants_ar_desc = intent.getStringExtra("Resturants_ar_desc");
             Resturants_en__desc = intent.getStringExtra("Resturants_en__desc");
            returant_id = intent.getIntExtra("id", 0);
            Resturants_en__desc = intent.getStringExtra("Resturants_en__desc");
            City_Name = intent.getStringExtra("City_Name");
            Resturants_en_address = intent.getStringExtra("Resturants_en_address");
            Resturants_ar_address = intent.getStringExtra("Resturants_ar_address");
            Resturants_phone = intent.getStringExtra("Resturants_phone");


            if (appLanguage.matches("ar")) {
                actionBar.setTitle(Resturants_ar_name);

            } else {
                actionBar.setTitle(Resturants_en_name);

            }

            mwebView.loadUrl(Resturants_link);
        } else {
            mwebView.loadUrl("https://www.google.com/maps");


        }
        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.favorite:

                final  Resturants resturantsfav = new Resturants(returant_id,
                        Resturants_ar_name,
                        Resturants_en_name,
                        Resturants_ar_desc,
                        Resturants_en__desc,
                      Resturants_link,
                      Resturants_phone,
                       Resturants_ar_address,
                       Resturants_en_address,
                        true,City_Name);
                if (ISLOGIN) {

                    mFirebaseFavDatabase.child(KEY_PHONE).
                           child("FavoriteResturant").child(returant_id+"")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.exists()) {
                                        mFirebaseFavDatabase.child(KEY_PHONE)
                                                .child("FavoriteResturant"). child(returant_id+"")
                                                .setValue(resturantsfav);
                                        isFavorite=true;

                                        menu.findItem(R.id.favorite)
                                                .setIcon(R.drawable.ic_favoritered);


                                    }

                                    else
                                    {

                                        mFirebaseFavDatabase.child(KEY_PHONE)
                                                .child("FavoriteResturant").
                                                child(returant_id+"")
                                                .setValue(resturantsfav)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                               ;
                                            }
                                        });
                                        isFavorite=true;
                                        menu.findItem(R.id.favorite)
                                                .setIcon(R.drawable.ic_favoritered);



                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                }


                else{
                    Toast(getString(R.string.mustlogin));
                }


                    return true;
            case R.id.comment:
                if(ISLOGIN) {
                    Intent intent = new Intent(getActiviy(), CommentActivityActivity.class);
                    intent.putExtra("comment_type", 1);
                    intent.putExtra("Resturants_ar_name", Resturants_ar_name);
                    intent.putExtra("Resturants_en_name", Resturants_en_name);
                    intent.putExtra("Resturants_en__desc", Resturants_en__desc);
                    intent.putExtra("Resturants_ar_desc", Resturants_ar_desc);
                    intent.putExtra("Resturants_link", Resturants_link);
                    intent.putExtra("City_Name", City_Name);
                    intent.putExtra("returant_id", returant_id);
                    intent.putExtra("Resturants_en_address", Resturants_en_address);
                    intent.putExtra("Resturants_ar_address", Resturants_ar_address);
                    intent.putExtra("Resturants_phone", Resturants_phone);

                    startActivity(intent);
                }


                else{
                    Toast(getString(R.string.mustlogin));
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.favorite_comment, menu);

        return true;
    }

    private class MyWebviewClient extends WebViewClient {
    }



    @Override
    protected void onStart() {
        super.onStart();
        if (ISLOGIN) {
            mFirebaseFavDatabase.child(KEY_PHONE).
                    child("FavoriteResturant").child(returant_id + "").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                    if (dataSnapshot.exists()) {
                        Resturants resturants = dataSnapshot.getValue(Resturants.class);
                        if (resturants.isIs_favorite()) {
                            menu.findItem(R.id.favorite).setIcon(R.drawable.ic_favoritered);


                        }

                    }
                    //}

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.favorite).setVisible(true);

        return super.onPrepareOptionsMenu(menu);
    }
}