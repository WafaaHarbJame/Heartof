package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.franmontiel.localechanger.LocaleChanger;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.MyHolder> {

    MyHolder holder;

    private final List<Resturants> resturants;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;



    public ResturantAdapter(Context context, List<Resturants> resturants) {
        this.resturants = resturants;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.resturant_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger=new SharedPManger(context);
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if (!(resturants.isEmpty())) {

            if(appLanguage.matches("ar")) {
                holder.mResturantsName.setText(resturants.get(position).getResturants_ar_name());
                holder.mResturantsAddress.setText(resturants.get(position).getResturants_ar_address());
                holder.mResturantsDesc.setText(resturants.get(position).getResturants_ar_desc());
                holder.mResturantsPhone.setText(resturants.get(position).getResturants_phone());
                holder.mResturantsLink.setText(resturants.get(position).getResturants_link());

            }
            else if(appLanguage.matches("en")) {
                holder.mResturantsName.setText(resturants.get(position).getResturants_en_name());
                holder.mResturantsAddress.setText(resturants.get(position).getResturants_en_address());
                holder.mResturantsDesc.setText(resturants.get(position).getResturants_en__desc());
                holder.mResturantsPhone.setText(resturants.get(position).getResturants_phone());
                holder.mResturantsLink.setText(resturants.get(position).getResturants_link());


            }




            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mResturantsName);

        }


    }


    @Override
    public int getItemCount() {
        return resturants.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView mResturantsName;
        private TextView mResturantsDesc;
        private TextView mResturantsPhone;
        private TextView mResturantsAddress;
        private TextView mResturantsLink;

        public MyHolder(View itemView) {
            super(itemView);
            mResturantsName = itemView.findViewById(R.id.Resturants_name);
            mResturantsDesc = itemView.findViewById(R.id.Resturants_desc);
            mResturantsPhone = itemView.findViewById(R.id.Resturants_phone);
            mResturantsAddress = itemView.findViewById(R.id.Resturants_address);
            mResturantsLink = itemView.findViewById(R.id.Resturants_link);
        }


    }

}



