package com.heartoftheworldapp.heartoftheworld.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.heartoftheworldapp.heartoftheworld.Model.ChooseLangauageBottomDialog;
import com.heartoftheworldapp.heartoftheworld.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingFragment extends BaseFragment {

    private CircleImageView mToolbarLogo;
    private TextView mTvMenuMyAccount;

    private TextView mTvLangauge;
    private View mView;
    private TextView mTvinformation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // شاشة الاعدادات لتغير اللغة
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
          mToolbarLogo = view.findViewById(R.id.toolbarLogo);
        mTvLangauge = view.findViewById(R.id.tvLangauge);
        mView = view.findViewById(R.id.view);
        mTvLangauge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseLangauageBottomDialog chooseLangauageBottomDialog = new ChooseLangauageBottomDialog();
                chooseLangauageBottomDialog.show(getActivity().getSupportFragmentManager(), chooseLangauageBottomDialog.getTag());

            }
        });
        return view;
    }
}