package com.heartoftheworldapp.heartoftheworld.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heartoftheworldapp.heartoftheworld.Adapter.CatogoryAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.HomeAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.Model.HomeClass;
import com.heartoftheworldapp.heartoftheworld.R;
import com.heartoftheworldapp.heartoftheworld.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBasicFragment extends Fragment {
    RecyclerView recyclerView;
    private HomeViewModel homeViewModel;

    List<HomeClass> homeClasses;
    HomeAdapter homeAdapter;
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView_cat;
    GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout mAllswip;



    public HomeBasicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home_basic, container, false);
        homeViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        mAllswip = view.findViewById(R.id.allswip);

        mAllswip.setColorSchemeResources
                (R.color.colorPrimary, android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark,
                        android.R.color.holo_blue_dark);

        mAllswip.setRefreshing(false);

        mAllswip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mAllswip.setRefreshing(false);



            }
        });
        recyclerView_cat = view.findViewById(R.id.recycler);
        gridLayoutManager=new GridLayoutManager(getContext(), 2);
        homeClasses = new ArrayList<>();
        homeClasses.clear();
        homeClasses.add(new HomeClass(1,getString(R.string.information), R.drawable.imformation,"information"));
        homeClasses.add(new HomeClass(2,getString(R.string.climate),  R.drawable.weather,"weather"));
        homeClasses.add(new HomeClass(3,getString(R.string.numberimportant), R.drawable.call3,"call"));
        homeClasses.add(new HomeClass(4,getString(R.string.Laws),  R.drawable.listicon1,"law"));
        homeClasses.add(new HomeClass(5,getString(R.string.advices),  R.drawable.travel,"trvael"));
        homeClasses.add(new HomeClass(6,getString(R.string.AlHasa),  R.drawable.alashaa,"AlHasa"));
        homeClasses.add(new HomeClass(7,getString(R.string.AlBaha),  R.drawable.bahaaa,"AlBaha"));
        homeClasses.add(new HomeClass(8,getString(R.string.Jazan), R.drawable.jasan,"Jazan"));
        homeClasses.add(new HomeClass(9,getString(R.string.Makkah ),R.drawable.makaa,"Makkah"));
        homeClasses.add(new HomeClass(10,getString(R.string.Medina), R.drawable.median,"Medina"));
        homeClasses.add(new HomeClass(11,getString(R.string.AlKhober), R.drawable.alkbar,"AlKhober"));
        homeClasses.add(new HomeClass(12,getString(R.string.Dammam),  R.drawable.damaa,"Dammam"));
        homeClasses.add(new HomeClass(13,getString(R.string.Hail),  R.drawable.haalaa,"Hail"));
        homeClasses.add(new HomeClass(14,getString(R.string.Riyadh),  R.drawable.tabuk,"Tabuk"));
        homeClasses.add(new HomeClass(15,getString(R.string.Tabuk),  R.drawable.rayadaa,"Riyadh"));
        homeClasses.add(new HomeClass(16,getString(R.string.Jeddah), R.drawable.jadaha,"Jeddah"));
        homeClasses.add(new HomeClass(17,getString(R.string.Abha), R.drawable.abha,"Abha"));
        recyclerView_cat.setLayoutManager(gridLayoutManager);
        homeAdapter = new HomeAdapter(getContext(), homeClasses);
        recyclerView_cat.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
        mAllswip.setRefreshing(false);

        if(homeClasses!=null){
            mAllswip.setRefreshing(false);

        }

        return view;
    }
}