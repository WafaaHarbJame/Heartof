package com.heartoftheworldapp.heartoftheworld.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Activity.ResturantActivity;
import com.heartoftheworldapp.heartoftheworld.Adapter.ResturantAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.ResturantFavoriteAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteResturantFragment extends BaseFragment {


    private RecyclerView mRecycler;
    private SwipeRefreshLayout mAllswip;
    private DatabaseReference mFirebaseDatabase;
    private List<Resturants> resturants;
    ResturantAdapter resturantFavoriteAdapter;
    boolean InternetConnect = false;
    private DatabaseReference mFirebaseFavDatabase;
    SharedPManger sharedPManger;
    String appLanguage;
    String KEY_PHONE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_favorite_resturant, container, false);
        mRecycler = root.findViewById(R.id.recycler);
        mAllswip = root.findViewById(R.id.allswip);
        mAllswip.setColorSchemeResources
                (R.color.colorPrimary, android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark,
                        android.R.color.holo_blue_dark);

        InternetConnect = CheckInternet();

        mFirebaseFavDatabase = FirebaseDatabase.getInstance().getReference("Favorites");

        sharedPManger = new SharedPManger(getActiviy());
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        KEY_PHONE = sharedPManger.getDataString(AppConstants.KEY_PHONE);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        resturants = new ArrayList<>();

        mRecycler.setLayoutManager(manager);
        mAllswip.setRefreshing(false);

        mAllswip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (InternetConnect) {

                    onStart();

                } else {
                    Toast(getString(R.string.checkInternet));


                }


            }
        });
        return  root;
    }

    @Override
    public void onStart() {
            resturants.clear();
            mAllswip.setRefreshing(true);

        mFirebaseFavDatabase.child(KEY_PHONE).
                child("FavoriteResturant").addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    resturants.clear();
                    for(DataSnapshot livenapshot:dataSnapshot.getChildren()) {
                        Resturants request = livenapshot.getValue(Resturants.class);
                        resturants.add(request);



                    }

                    resturantFavoriteAdapter = new ResturantAdapter( getContext(),resturants);
                    mRecycler.setAdapter(resturantFavoriteAdapter);
                    resturantFavoriteAdapter.notifyDataSetChanged();
                    mAllswip.setRefreshing(false);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            })
            ;
        mAllswip.setRefreshing(false);


        super.onStart();
    }
}
