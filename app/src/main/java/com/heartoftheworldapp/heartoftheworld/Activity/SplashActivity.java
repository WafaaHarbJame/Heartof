package com.heartoftheworldapp.heartoftheworld.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.franmontiel.localechanger.LocaleChanger;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.R;

public class SplashActivity extends BaseActivity {

    private ProgressBar progressBar;
    public static final int SPLASH_TIME = 2000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
// شاشة البداية عند فتح التطبيق بحيث يتم فحص المستخدم اذا كان قام بتسجيل الدخول فسوف يذهب الى الصفحة الرئيسية
        // أما اذا كان لا فسوف يذهب لشاشة الدخول ومن ثم يمكنها تسجيل الدخول أو الدخول كزائر
        //ويمكن الدخول الى التطبيق كزائر بدون تسجيل دخول

        SharedPreferences sharedPreferences = getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);
        if (sharedPreferences.getString(AppConstants.KEY_PHONE, null) != null &&
                sharedPreferences.getString(AppConstants.KEY_passward, null) != null) {


                if (sharedPreferences.getBoolean(AppConstants.ISLOGIN, false)) {

                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }



        } else {
            new CountDownTimer(SPLASH_TIME, 1000) {

                @Override
                public void onFinish() {


                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();

                }

                @Override
                public void onTick(long millisUntilFinished) {

                }
            }.start();

        }

    }



    @Override
    public void onBackPressed()
    {

        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }

}
