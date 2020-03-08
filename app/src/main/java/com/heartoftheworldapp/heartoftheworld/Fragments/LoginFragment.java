package com.heartoftheworldapp.heartoftheworld.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hbb20.CountryCodePicker;
import com.heartoftheworldapp.heartoftheworld.Activity.ForgetPasswordActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.LoginActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.MainActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.SignUpActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.addTouristOffice;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
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
    View view;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_login, container, false);
        mCcp = view.findViewById(R.id.ccp);
        mEtSignUpPhone = view.findViewById(R.id.etSignUpPhone);
        mEtSignInPassword = view.findViewById(R.id.etSignInPassword);
        mForgetpassward = view.findViewById(R.id.forgetpassward);
        mButtonSignInSign = view.findViewById(R.id.buttonSignInSign);
        mButtonSignInSignUp = view.findViewById(R.id.buttonSignInSignUp);
        mLoginvistor = view.findViewById(R.id.loginvistor);
        sharedPreferences = getActivity().getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);

        FirebaseInstanceId.getInstance().getToken();
        FirebaseApp.initializeApp(getActivity());
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

        mLoginvistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), addTouristOffice.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        if (sharedPreferences != null) {
            if (!((sharedPreferences.getString(AppConstants.KEY_EMAIL, "")).isEmpty() && (sharedPreferences.getString(AppConstants.KEY_passward, "")).isEmpty() && (sharedPreferences.getString(AppConstants.KEY_PHONE, "")).isEmpty())) {
                email = sharedPreferences.getString(AppConstants.KEY_EMAIL, "");
                password = sharedPreferences.getString(AppConstants.KEY_passward, "");
                phone = sharedPreferences.getString(AppConstants.KEY_PHONE, "");

                mEtSignUpPhone.setText(phone);
                mEtSignInPassword.setText(password);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        }
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
                    final String phone = mEtSignUpPhone.getText().toString();
                    final String password = mEtSignInPassword.getText().toString();
                    mFirebaseDatabase.child(CountryCode + mEtSignUpPhone.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    comparepassward = String.valueOf(dataSnapshot.child("passward").getValue());
                                    if (password.matches(comparepassward)) {
                                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);
                                        SharedPreferences.Editor editor_username = sharedPreferences.edit();
                                        editor_username.putString(AppConstants.KEY_PHONE, CountryCode + mEtSignUpPhone.getText().toString());
                                        editor_username.putString(AppConstants.KEY_passward, mEtSignInPassword.getText().toString());
                                        editor_username.apply();
                                        editor_username.commit();
                                        // Toast.makeText(LoginActivity.this, "the username exist", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(), MainActivity.class);


                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(getActivity(), "كلمة المرور خطأ ", Toast.LENGTH_SHORT).show();
                                        mEtSignInPassword.setText("");


                                    }


                                }


                            } else {

                                // mFirebaseDatabase.child(name).setValue(users);
                                Toast.makeText(getActivity(), "هذا الحساب غير موجود", Toast.LENGTH_SHORT).show();
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
        mButtonSignInSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        mForgetpassward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ForgetPasswordActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        mEtSignUpPhone.requestFocus();


        return view;
    }

}


