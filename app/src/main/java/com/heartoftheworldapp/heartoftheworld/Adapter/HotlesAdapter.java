package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HotlesAdapter extends RecyclerView.Adapter<HotlesAdapter.MyHolder> {

    MyHolder holder;

    private final List<Hotles> hotles;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;



    public HotlesAdapter(Context context, List<Hotles> hotles) {
        this.hotles = hotles;
        this.context = context;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hotlesitem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if (!(hotles.isEmpty())) {

            if (appLanguage.matches("ar")) {
                holder.mTvHotleName.setText(hotles.get(position).getHotle_ar_name());
                holder.mPrice.setText(hotles.get(position).getHotle_price());
                holder.mTvHotleDesc.setText(hotles.get(position).getHotle_ar_desc());
                Picasso.with(context).load(hotles.get(position).getHotle_image())
                        .error(R.drawable.logoo)
                        .into(holder.mUserimage);

            } else if (appLanguage.matches("en")) {
                holder.mTvHotleName.setText(hotles.get(position).getHotle_en_name());
                holder.mPrice.setText(hotles.get(position).getHotle_price());
                holder.mTvHotleDesc.setText(hotles.get(position).getHotle_en__desc());
                Picasso.with(context).load(hotles.get(position).getHotle_image())
                        .error(R.drawable.logoo)
                        .into(holder.mUserimage);

            }


            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mUserimage);

        }


    }


    @Override
    public int getItemCount() {
        return hotles.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTvHotleName;
        private TextView mTvHotleDesc;
        private TextView mPrice;
        private CircleImageView mUserimage;
        private ConstraintLayout mContainer;

        public MyHolder(View itemView) {
            super(itemView);
            mTvHotleName = itemView.findViewById(R.id.tv_hotle_name);
            mTvHotleDesc = itemView.findViewById(R.id.tv_hotle_desc);
            mPrice = itemView.findViewById(R.id.price);
            mUserimage = itemView.findViewById(R.id.userimage);
            mContainer = itemView.findViewById(R.id.container);
        }


    }

}



