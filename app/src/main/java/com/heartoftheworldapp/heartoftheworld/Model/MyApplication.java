package com.heartoftheworldapp.heartoftheworld.Model;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.franmontiel.localechanger.LocaleChanger;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class MyApplication extends Application {

    private RequestQueue requestQueue;
    private static MyApplication anInstance;
    SharedPreferences sharedPreferences;
    String appLanguage;
    SharedPreferences.Editor editor_signUp;
    SharedPManger sharedPManger;
    Context context;



    @Override
    public void onCreate() {
        super.onCreate();
        anInstance = this;
        sharedPManger=new SharedPManger(getApplicationContext());
        List<Locale> locales = new ArrayList<>();
        locales.add(new Locale("ar"));
        locales.add(new Locale("en"));

        LocaleChanger.initialize(getApplicationContext(), locales);

        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());

     if (appLanguage.equals("en")) {
            appLanguage = "en";
         sharedPManger.SetData(AppConstants.LANG_choose, appLanguage);
        }

        else   if (appLanguage.equals("ar")) {
          appLanguage = "ar";
         sharedPManger.SetData(AppConstants.LANG_choose, appLanguage);

     }

        LocaleChanger.setLocale(new Locale(appLanguage));


    }

    public void addToRequestQueue(Request request){
        if(getRequestQueue() !=null) {
            getRequestQueue().add(request);
        }
        else {
            requestQueue = Volley.newRequestQueue(this);
            getRequestQueue().add(request);
        }
    }

    private RequestQueue getRequestQueue(){
        return  requestQueue;
    }

    public void cancelRequest(String tag){
        getRequestQueue().cancelAll(tag);
    }

    public static synchronized MyApplication getInstance(){
        return anInstance;
    }

    private static Activity mCurrentActivity = null;
    public static Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        MyApplication.mCurrentActivity = mCurrentActivity;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        LocaleChanger.onConfigurationChanged();

    }
}
