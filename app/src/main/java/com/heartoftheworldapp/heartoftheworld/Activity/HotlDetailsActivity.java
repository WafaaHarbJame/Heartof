package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

public class HotlDetailsActivity extends BaseActivity {
    String Hotle_link, Hotle_ar_name, Hotle_en_name, Hotle_ar_desc, Hotle_en__desc, id, Hotle_image, City_Name;
    SharedPManger sharedPManger;
    String appLanguage;
    Boolean isFavorite = false;
    boolean ISLOGIN;
    String KEY_PHONE;
    int hotel_id;
    String url = "https://www.booking.com/index.ar.html?aid=318615&label=New_Arabic_AR_SA_20153462305-%2AzytRtGa3CC9mwRy77VlwQSM217246314918%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg&sid=e89d5b5660441b71ede60be0bd6fd476&click_from_logo=1";
    private Toolbar toolbar;
    private WebView mwebView;
    private DatabaseReference mFirebaseFavDatabase;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotl_details);
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
            Hotle_ar_name = intent.getStringExtra("Hotle_ar_name");
            Hotle_en_name = intent.getStringExtra("Hotle_en_name");
            Hotle_ar_desc = intent.getStringExtra("Hotle_ar_desc");
            Hotle_link = intent.getStringExtra("Hotle_link");
            Hotle_image = intent.getStringExtra("Hotle_image");
            hotel_id = intent.getIntExtra("id", 0);
            Hotle_en__desc = intent.getStringExtra("Hotle_en__desc");
            City_Name = intent.getStringExtra("City_Name");


            if (appLanguage.matches("ar")) {
                actionBar.setTitle(Hotle_ar_name);

            } else {
                actionBar.setTitle(Hotle_en_name);

            }

            mwebView.loadUrl(Hotle_link);
            if (!Hotle_link.startsWith("http")) {
                mwebView.loadUrl(url);

            }
        } else {
            mwebView.loadUrl(url);
        }
        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;


            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.loadUrl("https://www.booking.com/index.ar.html?aid=318615&label=New_Arabic_AR_SA_20153462305-%2AzytRtGa3CC9mwRy77VlwQSM217246314918%3Apl%3Ata%3Ap1%3Ap2%3Aac%3Aap%3Aneg&sid=e89d5b5660441b71ede60be0bd6fd476&click_from_logo=1");

                super.onReceivedError(view, request, error);
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
                final Hotles hotles = new Hotles(hotel_id, Hotle_ar_name, Hotle_en_name, Hotle_ar_desc, Hotle_en__desc, Hotle_link, "50", Hotle_image, true, City_Name);
                if (ISLOGIN) {
                    mFirebaseFavDatabase.child(KEY_PHONE).
                            child("FavoriteHotles").child(hotel_id + "")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                mFirebaseFavDatabase.child(KEY_PHONE)
                                        .child("FavoriteHotles").child(hotel_id + "").setValue(hotles);
                                isFavorite = true;

                                menu.findItem(R.id.favorite).setIcon(R.drawable.ic_favoritered);


                            } else {

                                mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteHotles").
                                        child(hotel_id + "").setValue(hotles).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        ;
                                    }
                                });
                                isFavorite = true;
                                menu.findItem(R.id.favorite).setIcon(R.drawable.ic_favoritered);


                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {

                    Toast(getString(R.string.mustlogin));
                }


                return true;
            case R.id.comment:
                if(ISLOGIN) {
                    Intent intent = new Intent(HotlDetailsActivity.this, CommentActivityActivity.class);
                    intent.putExtra("comment_type", 0);
                    intent.putExtra("Hotle_ar_name", Hotle_ar_name);
                    intent.putExtra("Hotle_en_name", Hotle_en_name);
                    intent.putExtra("Hotle_ar_desc", Hotle_ar_desc);
                    intent.putExtra("Hotle_en__desc", Hotle_en__desc);
                    intent.putExtra("Hotle_image", Hotle_image);
                    intent.putExtra("Hotle_link", Hotle_link);
                    intent.putExtra("hotel_id", hotel_id);
                    intent.putExtra("City_Name", City_Name);
                    Log.d("wafaa","hotel_id send"+hotel_id);
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
    protected void onStart() {
        super.onStart();
        if(ISLOGIN) {
            mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteHotles")
                    .child(hotel_id + "").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // for (DataSnapshot livenapshot : dataSnapshot.getChildren()) {
                    if (dataSnapshot.exists()) {

                        Hotles hotles1 = dataSnapshot.getValue(Hotles.class);
                        if (hotles1.isIs_favorite()) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.favorite_comment, menu);

        return true;
    }

    private class MyWebviewClient extends WebViewClient {
    }


}