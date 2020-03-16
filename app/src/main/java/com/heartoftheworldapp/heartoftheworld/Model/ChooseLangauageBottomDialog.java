package com.heartoftheworldapp.heartoftheworld.Model;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.franmontiel.localechanger.LocaleChanger;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.heartoftheworldapp.heartoftheworld.Activity.SplashActivity;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class ChooseLangauageBottomDialog extends BottomSheetDialogFragment {

    View viewDialog;
    ImageButton dialogButtonCancel;
    TextView dialogstatus, dialogpercent_satfication, dialogcost, tvSave;
    SharedPreferences sharedPreferences;
    boolean selectenglish = false;
    boolean selectarabic = false;
    String choosing_langauge;
    SharedPreferences.Editor editor_signUp;
    private ImageButton mButtonCancel;
    private TextView mTvChooselang;
    private TextView mTvSave;
    private TextView mArbiclang;
    private ImageView mSelectedar;
    private View mView2;
    private TextView mEnglishlang;
    private ImageView mSelecteden;
    SharedPManger sharedPManger;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        viewDialog = View.inflate(getContext(), R.layout.langauge_layout, null);
        dialog.setContentView(viewDialog);


        sharedPreferences = getActivity().getSharedPreferences(AppConstants.KEY_FILE, MODE_PRIVATE);
        sharedPManger=new SharedPManger(getActivity());

        mTvChooselang = viewDialog.findViewById(R.id.tvChooselang);
        mTvSave = viewDialog.findViewById(R.id.tvSave);
        mArbiclang = viewDialog.findViewById(R.id.arbiclang);
        mSelectedar = viewDialog.findViewById(R.id.selectedar);
        mView2 = viewDialog.findViewById(R.id.view2);
        mEnglishlang = viewDialog.findViewById(R.id.englishlang);
        mSelecteden = viewDialog.findViewById(R.id.selecteden);
        mButtonCancel = viewDialog.findViewById(R.id.buttonCancel);
        final Configuration config = new Configuration();
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });

        if (sharedPreferences != null) {
            if (sharedPreferences.getString(AppConstants.LANG_choose, null) != null) {
                choosing_langauge = sharedPreferences.getString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());
                if (choosing_langauge.equals("ar")) {
                    mSelectedar.setVisibility(View.VISIBLE);

                } else {
                    mSelecteden.setVisibility(View.VISIBLE);

                }


            }
        }
        mArbiclang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectarabic) {
                    mSelectedar.setVisibility(View.GONE);
                    selectarabic = false;


                } else {
                    selectarabic = true;
                    mSelectedar.setVisibility(View.VISIBLE);
                    mSelecteden.setVisibility(View.GONE);

                }


            }
        });
        mEnglishlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectenglish) {
                    mSelecteden.setVisibility(View.GONE);
                    selectenglish = false;


                } else {
                    selectenglish = true;
                    mSelecteden.setVisibility(View.VISIBLE);
                    mSelectedar.setVisibility(View.GONE);

                }


            }
        });

        mTvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectenglish) {
                    editor_signUp = sharedPreferences.edit();
                    editor_signUp.putString(AppConstants.LANG_choose, "en");
                    sharedPManger.SetData(AppConstants.LANG_choose, "en");

                    editor_signUp.apply();
                    editor_signUp.commit();
                    LocaleChanger.setLocale(new Locale("en"));
                    Intent refresh = new Intent(getActivity(), SplashActivity.class);
                    getActivity().finish();
                    startActivity(refresh);


                } else {

                    editor_signUp = sharedPreferences.edit();
                    editor_signUp.putString(AppConstants.LANG_choose, "ar");
                    editor_signUp.apply();
                    editor_signUp.commit();
                    sharedPManger.SetData(AppConstants.LANG_choose, "ar");

                    LocaleChanger.setLocale(new Locale("ar"));
                    Intent refresh = new Intent(getActivity(), SplashActivity.class);
                    getActivity().finish();
                    startActivity(refresh);

                }
            }
        });

    }


    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), SplashActivity.class);
        getActivity().finish();
        startActivity(refresh);
        // restartActivity();
    }


    private void restartActivity() {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }

}
