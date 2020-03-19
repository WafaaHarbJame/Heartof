package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.franmontiel.localechanger.LocaleChanger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hbb20.CountryCodePicker;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Locale;

public class LoginActivity extends BaseActivity {

    String email, password, phone;
    String comparepassward;
    String fcm_token;
    boolean select_country = false;
    String CountryCode = "+966";
    SharedPreferences sharedPreferences;
    private CountryCodePicker mCcp;
    private EditText mEtSignUpPhone;
    private EditText mEtSignInPassword;
    private TextView mForgetpassward;
    private Button mButtonSignInSign;
    private Button mButtonSignInSignUp;
    private DatabaseReference mFirebaseDatabase;
    private TextView mLoginvistor;
    SharedPManger sharedPManger;
    private Button mAddingDataForAplication;
    private RadioButton mEnglishlang;
    private RadioButton mARABIClang;
    private RadioGroup mGroupradio;
    String choosing_langauge;
    SharedPreferences.Editor editor_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // شاشة الدخول
        //هذة R.layout activity_login   الخاصة بتصميم الشاشة يمكنك الذهاب اليها بالضغط على ctrl+b لرؤية التصميم

        setContentView(R.layout.activity_login);
        mCcp = findViewById(R.id.ccp);
        mEtSignUpPhone = findViewById(R.id.etSignUpPhone);
        mEtSignInPassword = findViewById(R.id.etSignInPassword);
        mForgetpassward = findViewById(R.id.forgetpassward);
        mButtonSignInSign = findViewById(R.id.buttonSignInSign);
        mButtonSignInSignUp = findViewById(R.id.buttonSignInSignUp);
        mLoginvistor = findViewById(R.id.loginvistor);
        sharedPManger = new SharedPManger(getActiviy());
        FirebaseInstanceId.getInstance().getToken();
        mAddingDataForAplication = findViewById(R.id.adding_data_for_aplication);
        mEnglishlang = findViewById(R.id.englishlang);
        mARABIClang = findViewById(R.id.ARABIClang);
        mGroupradio = findViewById(R.id.groupradio);
        FirebaseApp.initializeApp(this);
        sharedPreferences = getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);

        Configuration config = new Configuration();

        sharedPManger=new SharedPManger(LoginActivity.this);
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());
        // فحص اللغة المخزنة

        if (appLanguage.matches("en")) {
            config.locale = Locale.ENGLISH;
            Locale.setDefault(new Locale("en", "US"));
            setLocale("en");
            getResources().updateConfiguration(config, null);
            mEnglishlang.setChecked(true);
            LocaleChanger.setLocale(new Locale("en"));


        } else {
            Locale arabic = new Locale("ar", "ar");
            config.locale = arabic;
            Locale.setDefault(arabic);
            setLocale("ar");
            getResources().updateConfiguration(config, null);
            mARABIClang.setChecked(true);
            LocaleChanger.setLocale(new Locale("ar"));


        }


        mGroupradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton =mGroupradio.findViewById(checkedId);
                if(radioButton.getText().equals(getString(R.string.englishlang))){
                    editor_signUp = sharedPreferences.edit();
                    editor_signUp.putString(AppConstants.LANG_choose, "en");
                    sharedPManger.SetData(AppConstants.LANG_choose, "en");
                    editor_signUp.apply();
                    editor_signUp.commit();
                    LocaleChanger.setLocale(new Locale("en"));

                }
                else {
                    editor_signUp = sharedPreferences.edit();
                    editor_signUp.putString(AppConstants.LANG_choose, "ar");
                    sharedPManger.SetData(AppConstants.LANG_choose, "ar");
                    editor_signUp.apply();
                    editor_signUp.commit();
                    LocaleChanger.setLocale(new Locale("ar"));


                }
                recreate();

            }
        });
        mCcp.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                select_country = true;
                return false;
            }
        });

        mCcp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                select_country = true;
                CountryCode = mCcp.getSelectedCountryCodeWithPlus();
            }
        });

        mAddingDataForAplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AddingActivity.class);
                startActivity(intent);
            }
        });

        mLoginvistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        sharedPreferences = getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);
        if (sharedPreferences != null) {
            if (!((sharedPreferences.getString(AppConstants.KEY_EMAIL, "")).isEmpty() && (sharedPreferences.getString(AppConstants.KEY_passward, "")).isEmpty() && (sharedPreferences.getString(AppConstants.KEY_PHONE, "")).isEmpty())) {
                email = sharedPreferences.getString(AppConstants.KEY_EMAIL, "");
                password = sharedPreferences.getString(AppConstants.KEY_passward, "");
                phone = sharedPreferences.getString(AppConstants.KEY_PHONE, "");
                mEtSignUpPhone.setText(phone);
                mEtSignInPassword.setText(password);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
        // فحص بيانات اليوزر المدخلة مع البيانات المخزنة في الفابيرس
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Users").child(mEtSignUpPhone.getText().toString());

        mButtonSignInSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtSignUpPhone.getText().toString().equals(null) || mEtSignUpPhone.getText().toString().equals("")) {
                    mEtSignUpPhone.setError(getString(R.string.phoneRequired));
                    mEtSignUpPhone.requestFocus();

                } else if (mEtSignInPassword.getText().toString().equals(null) || mEtSignInPassword.getText().toString().equals("")) {
                    mEtSignInPassword.setError(getString(R.string.passwordRequired));
                    mEtSignInPassword.requestFocus();
                } else {
                    showProgreesDilaog(getActiviy(), getString(R.string.login), getString(R.string.logintxt));

                    final String phone = mEtSignUpPhone.getText().toString();
                    final String password = mEtSignInPassword.getText().toString();
                    mFirebaseDatabase.child(CountryCode + mEtSignUpPhone.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                            if (dataSnapshot.exists()) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    comparepassward = String.valueOf(dataSnapshot.child("passward").getValue());
                                    String username = String.valueOf(dataSnapshot.child("username").getValue());
                                    sharedPManger.SetData(AppConstants.KEY_username, username);

                                    if (password.matches(comparepassward)) {
                                        sharedPManger.SetData(AppConstants.KEY_PHONE, CountryCode + mEtSignUpPhone.getText().toString());
                                        sharedPManger.SetData(AppConstants.KEY_passward, mEtSignInPassword.getText().toString());
                                        sharedPManger.SetData(AppConstants.KEY_username, username);

                                        sharedPManger.SetData(AppConstants.ISLOGIN, true);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        hideProgreesDilaog(getActiviy(), getString(R.string.login), getString(R.string.logintxt));


                                    } else {
                                        Toast.makeText(LoginActivity.this, "كلمة المرور خطأ ", Toast.LENGTH_SHORT).show();
                                        mEtSignInPassword.setText("");
                                        hideProgreesDilaog(getActiviy(), getString(R.string.login), getString(R.string.logintxt));


                                    }

                                    hideProgreesDilaog(getActiviy(), getString(R.string.login), getString(R.string.logintxt));

                                }


                            } else {
                                hideProgreesDilaog(getActiviy(), getString(R.string.login), getString(R.string.logintxt));

                                // mFirebaseDatabase.child(name).setValue(users);
                                Toast.makeText(LoginActivity.this, "هذا الحساب غير موجود", Toast.LENGTH_SHORT).show();
                                mEtSignUpPhone.setText("");
                                mEtSignInPassword.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
        // اذا كان ليس لديه حساب سوف يذهب الى شاشة التسجيل
        mButtonSignInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // اذا كان  لديه حساب ونسي كلمة المرو سوف يذهب الى شاشة تغير كلمة المرور

        mForgetpassward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mEtSignUpPhone.requestFocus();



    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //Intent refresh = new Intent(this, AndroidLocalize.class);
        //finish();
        //startActivity(refresh);
    }

}
