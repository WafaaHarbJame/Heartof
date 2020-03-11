package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Milestonse;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteMilestonesAdapter extends RecyclerView.Adapter<FavoriteMilestonesAdapter.MyHolder> {

    MyHolder holder;

    private final List<Milestonse> milestonses;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean mFavorite=true;
    Boolean isFavorite;


    public FavoriteMilestonesAdapter(Context context, List<Milestonse> milestonses) {
        this.milestonses = milestonses;
        this.context = context;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.milstoneitem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if (!(milestonses.isEmpty())) {

            if (appLanguage.matches("ar")) {
                holder.mTvMilestonesdesc.setText(milestonses.get(position).getMilestonse__ar_desc());
                Picasso.with(context).load(milestonses.get(position).getMilestonse_image())
                        .error(R.drawable.logoo).into(holder.mUserimage);
            } else if (appLanguage.matches("en")) {
                holder.mTvMilestonesdesc.setText(milestonses.get(position).getMilestonse__en__desc());
                Picasso.with(context).load(milestonses.get(position).getMilestonse_image())
                        .error(R.drawable.logoo).into(holder.mUserimage);

            }


            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mUserimage);

        }


    }


    @Override
    public int getItemCount() {
        return milestonses.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTvMilestonesdesc;
        private CircleImageView mUserimage;
        private ConstraintLayout mContainer;
        private ImageView mIcFavorit;


        public MyHolder(View itemView) {
            super(itemView);

            mTvMilestonesdesc = itemView.findViewById(R.id.tv_milestonesdesc);
            mUserimage = itemView.findViewById(R.id.userimage);
            mContainer = itemView.findViewById(R.id.container);
            mIcFavorit = itemView.findViewById(R.id.ic_favorit);

        }


    }

}



