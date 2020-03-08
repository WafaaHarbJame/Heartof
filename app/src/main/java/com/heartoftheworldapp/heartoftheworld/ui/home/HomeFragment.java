package com.heartoftheworldapp.heartoftheworld.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heartoftheworldapp.heartoftheworld.Adapter.CatogoryAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView recyclerView;
    List<Catogory> catogories;
    CatogoryAdapter catogoryAdapter;
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView_cat;
    GridLayoutManager gridLayoutManager;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        recyclerView_cat = view.findViewById(R.id.recycler);
        gridLayoutManager=new GridLayoutManager(getContext(), 2);
        catogories = new ArrayList<>();
        catogories.add(new Catogory(4,getString(R.string.information), "https://bizbangladesh.net/public/uploads/news/1559040261.png",""));
        catogories.add(new Catogory(5,getString(R.string.Restaurants), "https://www.ahstatic.com/photos/5394_rsr001_00_p_1024x768.jpg",""));
        catogories.add(new Catogory(2,getString(R.string.hotels), "https://ihg.scene7.com/is/image/ihg/even-hotels-eugene-5405616297-4x3",""));
        catogories.add(new Catogory(3,getString(R.string.climate), "https://ichef.bbci.co.uk/images/ic/1200x675/p0617tch.jpg",""));
        catogories.add(new Catogory(1,getString(R.string.Tourist), "data:image/jpeg;base64,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",""));
        catogories.add(new Catogory(6,getString(R.string.milestones), "https://albenaa-wpengine.netdna-ssl.com/wp-content/uploads/2014/04/13.jpg",""));
        catogories.add(new Catogory(7,getString(R.string.numberimportant), "https://albenaa-wpengine.netdna-ssl.com/wp-content/uploads/2014/04/13.jpg",""));
        catogories.add(new Catogory(8,getString(R.string.caroffices),
                "https://previews.123rf.com/images/graphicbee/graphicbee1705/graphicbee170500043/77464389-yellow-taxi-car-and-taxi-driver.jpg",""));

        recyclerView_cat.setLayoutManager(gridLayoutManager);
        catogoryAdapter = new CatogoryAdapter(getContext(), catogories);
        recyclerView_cat.setAdapter(catogoryAdapter);
        return view;
    }
}