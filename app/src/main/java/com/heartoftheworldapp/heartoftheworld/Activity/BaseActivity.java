package com.heartoftheworldapp.heartoftheworld.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.franmontiel.localechanger.LocaleChanger;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    SharedPManger sharedPManger;
    String appLanguage;
    Context context;
    AwesomeProgressDialog awesomeProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPManger=new SharedPManger(getApplicationContext());
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        sharedPManger=new SharedPManger(BaseActivity.this);
        LocaleChanger.setLocale(new Locale(appLanguage));



    }



    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }


    protected  Activity  Toast(){
        return this;

    }

    protected  Activity  getActiviy(){
        return this;

    }

    public void hideDialog() {

        if (progressDialog.isShowing()) progressDialog.dismiss();
    }
    public void Toast(String msg) {

        Toast.makeText(getActiviy(), msg, Toast.LENGTH_SHORT).show();
    }

    public void Toast(int resId) {

        Toast.makeText(getActiviy(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    protected boolean CheckInternet() {
        boolean connected = false;

        ConnectivityManager conMgr = (ConnectivityManager) BaseActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            connected = true;

        } else {
            connected = false;

        }

        return connected;

    }
    public void showProgreesDilaog(Activity activity, String tittle, String message) {
        awesomeProgressDialog = new AwesomeProgressDialog(activity);
        awesomeProgressDialog.setTitle(tittle).setMessage(message).
                setColoredCircle(R.color.colorPrimaryDark).setDialogIconAndColor(R.drawable.ic_dialog_info,
                R.color.white).setCancelable(true).show();

    }

    public void hideProgreesDilaog(Activity activity, String tittle, String message) {
        if(awesomeProgressDialog!=null){
            awesomeProgressDialog.hide();

        }


    }

}

