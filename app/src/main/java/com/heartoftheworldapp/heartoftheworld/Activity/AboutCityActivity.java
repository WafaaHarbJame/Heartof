package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.heartoftheworldapp.heartoftheworld.Model.PlayerConfig;
import com.heartoftheworldapp.heartoftheworld.R;

public class AboutCityActivity extends BaseActivity {
    Toolbar toolbar;
    String city_name;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private TextView mTitle;
    private TextView mTvcontent;
    private Toolbar mToolbar;
    private VideoView mSimpleVideoView;
    private YouTubePlayerView mYoutubeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_city);
        toolbar = findViewById(R.id.toolbar);
        mTitle = findViewById(R.id.title);
        mTvcontent = findViewById(R.id.tvcontent);
        setSupportActionBar(toolbar);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.aboutcity));
        Intent intent = getIntent();

        if (intent != null) {
            city_name = intent.getStringExtra("cityname");
        }


        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)
                getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youtubeFragment.initialize(PlayerConfig.API_KEY,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.


                        if (city_name.matches("Makkah")) {
                            youTubePlayer.loadVideo("9FoEbJx8ZP4");

                        }
                        if (city_name.matches("AlKhober")) {
                            youTubePlayer.loadVideo("ehZNCkivAW0");


                        }
                        if (city_name.matches("Abha")) {
                            youTubePlayer.loadVideo("eW09U8YuypY");


                        }   if (city_name.matches("Riyadh")) {
                            youTubePlayer.loadVideo("SBpPZUy0ksE");


                        }   if (city_name.matches("Jeddah")) {
                            youTubePlayer.loadVideo("aZikF1xeuA0");


                        }
                        if (city_name.matches("Tabuk")) {
                            youTubePlayer.loadVideo("P8mxSX3nT68");


                        }

                        if (city_name.matches("Hail")) {
                            youTubePlayer.loadVideo("jQTnOjUs4fE");


                        }
                        if (city_name.matches("AlBaha")) {
                            youTubePlayer.loadVideo("-IK07Ez_YDQ");


                        }
                        if (city_name.matches("Jazan")) {
                            youTubePlayer.loadVideo("LTV60QKd3TE");


                        }

                        if (city_name.matches("Medina")) {
                            youTubePlayer.loadVideo("NQrwpxcQeOA");


                        }

                        if (city_name.matches("Dammam")) {
                            youTubePlayer.loadVideo("5SZn0_jzZJY");


                        }


                        if (city_name.matches("AlHasa")) {
                            youTubePlayer.loadVideo("BHThdzMZtVQ");


                        }
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
       // mYoutubeView.initialize(PlayerConfig.API_KEY, onInitializedListener);

        mTvcontent = findViewById(R.id.tvcontent);
        if (city_name.matches("AlHasa")) {
            mTitle.setText(getString(R.string.AlHasa));
            mTvcontent.setText(getString(R.string.AlHasaabout));

        }

        if (city_name.matches("AlBaha")) {
            mTitle.setText(getString(R.string.AlBaha));
            mTvcontent.setText(getString(R.string.AlBahaabout));

        }

        if (city_name.matches("Abha")) {
            mTitle.setText(getString(R.string.Abha));
            mTvcontent.setText(getString(R.string.Abhaabout));

        }

        if (city_name.matches("Jazan")) {
            mTitle.setText(getString(R.string.Jazan));
            mTvcontent.setText(getString(R.string.Jazanabout));

        }
        if (city_name.matches("Makkah")) {
            mTitle.setText(getString(R.string.Makkah));
            mTvcontent.setText(getString(R.string.Makkahabout));

        }
        if (city_name.matches("Medina")) {
            mTitle.setText(getString(R.string.Medina));
            mTvcontent.setText(getString(R.string.Medinaabout));

        }
        if (city_name.matches("Dammam")) {
            mTitle.setText(getString(R.string.Dammam));
            mTvcontent.setText(getString(R.string.DamaaAbout));

        }
        if (city_name.matches("AlKhober")) {
            mTitle.setText(getString(R.string.AlKhober));
            mTvcontent.setText(getString(R.string.AlKhoberabout));

        }

        if (city_name.matches("Riyadh")) {
            mTitle.setText(getString(R.string.Riyadh));
            mTvcontent.setText(getString(R.string.Riyadhabout));

        }

        if (city_name.matches("Hail")) {
            mTitle.setText(getString(R.string.Hail));
            mTvcontent.setText(getString(R.string.Hailabout));

        }

        if (city_name.matches("Jeddah")) {
            mTitle.setText(getString(R.string.Jeddah));
            mTvcontent.setText(getString(R.string.Jeddahabout));

        }
        if (city_name.matches("Tabuk")) {
            mTitle.setText(getString(R.string.Tabuk));
            mTvcontent.setText(getString(R.string.Tabukabout));

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
