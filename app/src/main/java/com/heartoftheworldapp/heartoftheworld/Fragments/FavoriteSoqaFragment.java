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
import com.heartoftheworldapp.heartoftheworld.Adapter.FavoriteSoqaAdapter;
import com.heartoftheworldapp.heartoftheworld.Adapter.ResturantFavoriteAdapter;
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.ArrayList;
import java.util.List;


public class FavoriteSoqaFragment extends BaseFragment {

    private RecyclerView mRecycler;
    private SwipeRefreshLayout mAllswip;
    private DatabaseReference mFirebaseDatabase;
    private List<Alsoaq> alsoaqs;
    FavoriteSoqaAdapter favoriteSoqaAdapter;
    boolean InternetConnect = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root;
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_favorite_soqa, container, false);
        mRecycler = root.findViewById(R.id.recycler);
        mAllswip = root.findViewById(R.id.allswip);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Alasqaa");
        mAllswip.setColorSchemeResources
                (R.color.colorPrimary, android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark,
                        android.R.color.holo_blue_dark);

        InternetConnect = CheckInternet();




        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActiviy());
        alsoaqs = new ArrayList<>();

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
        alsoaqs.clear();
        mAllswip.setRefreshing(true);

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alsoaqs.clear();
                for(DataSnapshot livenapshot:dataSnapshot.getChildren()) {
                    for(DataSnapshot live1snapshot:livenapshot.getChildren()) {

                        Alsoaq request = live1snapshot.getValue(Alsoaq.class);
                        if (request.isIs_favorite()) {
                            alsoaqs.add(request);

                        }
                    }


                }

                favoriteSoqaAdapter = new FavoriteSoqaAdapter( getActivity(),alsoaqs);
                mRecycler.setAdapter(favoriteSoqaAdapter);
                favoriteSoqaAdapter.notifyDataSetChanged();
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
