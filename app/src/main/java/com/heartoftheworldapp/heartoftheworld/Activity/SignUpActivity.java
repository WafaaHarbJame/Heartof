package com.heartoftheworldapp.heartoftheworld.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.Model.Users;
import com.heartoftheworldapp.heartoftheworld.R;

public class SignUpActivity extends BaseActivity {

    private EditText mEtSignUpFullName;
    private EditText mEtSignUpEmail;
    private EditText mEtSignUpPassword;
    private EditText mEtSignUpConfirmPassword;
    private CountryCodePicker mCcp;
    private EditText mEtSignUpPhone;
    private Button mButtonSignUpSign;
    private Button mButtonSignUpClickHere;
    private DatabaseReference mFirebaseDatabase;
    boolean select_country = false;
    String CountryCode = "+966";
    private CountryCodePicker ccp;
    private ImageView mBack;
    SharedPreferences sharedPreferences;
    SharedPManger sharedPManger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEtSignUpFullName = findViewById(R.id.etSignUpFullName);
        mEtSignUpEmail = findViewById(R.id.etSignUpEmail);
        mEtSignUpPassword = findViewById(R.id.etSignUpPassword);
        mEtSignUpConfirmPassword = findViewById(R.id.etSignUpConfirmPassword);
        mCcp = findViewById(R.id.ccp);
        mEtSignUpPhone = findViewById(R.id.etSignUpPhone);
        mButtonSignUpSign = findViewById(R.id.buttonSignUpSign);
        mButtonSignUpClickHere = findViewById(R.id.buttonSignUpClickHere);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mBack = findViewById(R.id.back);
        sharedPreferences = getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);
            sharedPManger=new SharedPManger(getActiviy());

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


        mButtonSignUpClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        mButtonSignUpSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEtSignUpFullName.getText().toString().equals(null) || mEtSignUpFullName.getText().toString().equals("")) {
                    mEtSignUpFullName.setError(getString(R.string.fullNameRequired));
                    mEtSignUpFullName.requestFocus();


                } else if (mEtSignUpEmail.getText().toString().equals(null) || mEtSignUpEmail.getText().toString().equals("")) {
                    mEtSignUpEmail.setError(getString(R.string.emailRequired));
                    mEtSignUpEmail.requestFocus();

                } else if (!isEmailValid(mEtSignUpEmail.getText().toString())) {
                    mEtSignUpEmail.setError(getString(R.string.enter__correct_email));
                    mEtSignUpEmail.requestFocus();


                } else if (mEtSignUpPassword.getText().toString().equals(null) || mEtSignUpPassword.getText().toString().equals("")) {
                    mEtSignUpPassword.setError(getString(R.string.passwordRequired));
                    mEtSignUpPassword.requestFocus();


                    Toast.makeText(SignUpActivity.this, "" + getString(R.string.passwordRequired), Toast.LENGTH_SHORT).show();
                } else if (mEtSignUpConfirmPassword.getText().toString().equals(null) || mEtSignUpConfirmPassword.getText().toString().equals("")) {
                    mEtSignUpConfirmPassword.setError(getString(R.string.confirmPasswordRequired));
                    mEtSignUpConfirmPassword.requestFocus();


                } else if (!mEtSignUpConfirmPassword.getText().toString().equals(mEtSignUpPassword.getText().toString())) {

                    mEtSignUpConfirmPassword.setError(getString(R.string.confirmPasswordNotMatchesPassword));


                } else if (mEtSignUpPhone.getText().toString().equals(null) || mEtSignUpPhone.getText().toString().equals("")) {

                    mEtSignUpPhone.setError(getString(R.string.phoneRequired));
                    mEtSignUpPhone.requestFocus();

                } else {
                    showProgreesDilaog(getActiviy(),getString(R.string.login),getString(R.string.logintxt));

                    final String name = mEtSignUpFullName.getText().toString();
                    final String id = mFirebaseDatabase.push().getKey();
                    final String email = mEtSignUpEmail.getText().toString();
                    final String phone = CountryCode + mEtSignUpPhone.getText().toString();

                    final Users users = new Users(mEtSignUpFullName.getText().toString(), mEtSignUpEmail.getText().toString(), mEtSignUpPassword.getText().toString(), CountryCode + mEtSignUpPhone.getText().toString());
                    mFirebaseDatabase.child(phone).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(SignUpActivity.this, "هذا الرقم مسجل مسبقا", Toast.LENGTH_SHORT).show();
                                mEtSignUpEmail.setText("");
                                mEtSignUpFullName.setText("");
                                mEtSignUpPassword.setText("");
                                mEtSignUpPhone.setText("");
                                mEtSignUpConfirmPassword.setText("");


                            } else {
                                mFirebaseDatabase.child(phone).setValue(users);
                                Toast.makeText(SignUpActivity.this, "تم التسجيل بنجاج  ", Toast.LENGTH_SHORT).show();
                                sharedPManger.SetData(AppConstants.KEY_EMAIL, mEtSignUpEmail.getText().toString());
                                sharedPManger.SetData(AppConstants.KEY_passward, mEtSignUpPassword.getText().toString());
                                sharedPManger.SetData(AppConstants.KEY_PHONE, CountryCode + mEtSignUpPhone.getText().toString());
                                sharedPManger.SetData(AppConstants.KEY_username, mEtSignUpFullName.getText().toString());
                                sharedPManger.SetData(AppConstants.ISLOGIN,true);
                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                intent.putExtra(AppConstants.KEY_EMAIL, mEtSignUpEmail.getText().toString());
                                intent.putExtra(AppConstants.KEY_passward, mEtSignUpPassword.getText().toString());
                                intent.putExtra(AppConstants.KEY_PHONE, CountryCode + mEtSignUpPhone.getText().toString());
                                intent.putExtra(AppConstants.KEY_username, mEtSignUpFullName.getText().toString());

                                startActivity(intent);

                            }
                            hideProgreesDilaog(getActiviy(),getString(R.string.signUp),getString(R.string.signUptext));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }


            }
        });

    }
}
