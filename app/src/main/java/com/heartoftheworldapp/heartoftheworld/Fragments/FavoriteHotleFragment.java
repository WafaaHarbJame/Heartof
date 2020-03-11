package com.heartoftheworldapp.heartoftheworld.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Adapter.FavoriteHotlesAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.FavoriteMilestonesAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.HotlesAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.Model.Milestonse;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;


public class FavoriteHotleFragment extends BaseFragment {
    private RecyclerView mRecycler;
    private SwipeRefreshLayout mAllswip;
    private DatabaseReference mFirebaseDatabase;
    private List<Hotles> hotles;
    HotlesAdapter favoriteHotlesAdapter;
    boolean InternetConnect = false;
    private DatabaseReference mFirebaseFavDatabase;
    SharedPManger sharedPManger;
    String appLanguage;
    String KEY_PHONE;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_favorite_hotle, container, false);
        mRecycler = root.findViewById(R.id.recycler);
        mAllswip = root.findViewById(R.id.allswip);
        mFirebaseFavDatabase = FirebaseDatabase.getInstance().getReference("Favorites");
        mAllswip.setColorSchemeResources
                (R.color.colorPrimary, android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark,
                        android.R.color.holo_blue_dark);

        InternetConnect = CheckInternet();



        sharedPManger = new SharedPManger(getActiviy());
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        KEY_PHONE = sharedPManger.getDataString(AppConstants.KEY_PHONE);


        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        hotles = new ArrayList<>();

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
        hotles.clear();
        mAllswip.setRefreshing(true);

        mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteHotles").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotles.clear();
                for(DataSnapshot livenapshot:dataSnapshot.getChildren()) {

                        Hotles request = livenapshot.getValue(Hotles.class);
                        if (request.isIs_favorite()) {
                            hotles.add(request);

                        }

                    favoriteHotlesAdapter = new HotlesAdapter(getContext(),hotles);
                    mRecycler.setAdapter(favoriteHotlesAdapter);

                }


                favoriteHotlesAdapter.notifyDataSetChanged();
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
