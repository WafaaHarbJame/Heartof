package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Offices;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.List;

public class OfficeAdapter extends RecyclerView.Adapter<OfficeAdapter.MyHolder> {

    MyHolder holder;

    private final List<Offices> offices;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;



    public OfficeAdapter(Context context, List<Offices> offices) {
        this.offices = offices;
        this.context = context;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.officeitem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if (!(offices.isEmpty())) {

            if (appLanguage.matches("ar")) {
                holder.mOfficename.setText(offices.get(position).getOfficearname());
                holder.mOfficenumber.setText(offices.get(position).getOfficenumber());
                holder.mOfficetype.setText(offices.get(position).getOfficetype_ar());

            } else if (appLanguage.matches("en")) {
                holder.mOfficename.setText(offices.get(position).getOfficeenname());
                holder.mOfficenumber.setText(offices.get(position).getOfficenumber());
                holder.mOfficetype.setText(offices.get(position).getOfficetype_en());

            }


            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mOfficename);

        }


    }


    @Override
    public int getItemCount() {
        return offices.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView mOfficetype;
        private TextView mOfficename;
        private TextView mOfficenumber;

        public MyHolder(View itemView) {
            super(itemView);
            mOfficetype = itemView.findViewById(R.id.officetype);
            mOfficename = itemView.findViewById(R.id.officename);
            mOfficenumber = itemView.findViewById(R.id.officenumber);
        }


    }

}



