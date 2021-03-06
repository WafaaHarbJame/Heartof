package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heartoftheworldapp.heartoftheworld.Activity.HotlDetailsActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.LocationActivity;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class HotlesAdapter extends RecyclerView.Adapter<HotlesAdapter.MyHolder> {

    MyHolder holder;

    private final List<Hotles> hotles;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean ISLOGIN;



    boolean mFavorite=true;
    Boolean isFavorite;
    private DatabaseReference mFirebaseDatabase;

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
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Hotles");
          ISLOGIN= sharedPManger.getDataBool(AppConstants.ISLOGIN,false);

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
                if(hotles.get(position).getHotle_image()==null||hotles.get(position).getHotle_image().matches("")){
                    Picasso.with(context).load("https://r-cf.bstatic.com/images/hotel/max1024x768/106/10656035.jpg")
                            .error(R.drawable.logoo)
                            .into(holder.mUserimage);
                }
                else {
                    Picasso.with(context).load(hotles.get(position).getHotle_image())
                            .error(R.drawable.logoo)
                            .into(holder.mUserimage);

                }


            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HotlDetailsActivity.class);
                    intent.putExtra("Hotle_link", hotles.get(position).getHotle_link());
                    intent.putExtra("Hotle_ar_name", hotles.get(position).getHotle_ar_name());
                    intent.putExtra("Hotle_en_name", hotles.get(position).getHotle_en_name());
                    intent.putExtra("Hotle_ar_desc", hotles.get(position).getHotle_ar_desc());
                    intent.putExtra("City_Name", hotles.get(position).getCity_Name());
                    intent.putExtra("id", hotles.get(position).getId());
                    intent.putExtra("Hotle_en__desc", hotles.get(position).getHotle_en__desc());
                    intent.putExtra("Hotle_image", hotles.get(position).getHotle_image());


                    context.startActivity(intent);
                }
            });

            isFavorite = hotles.get(position).isIs_favorite();

            if (isFavorite) {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
            } else {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));

            }

            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mUserimage);

        }


    }


    @Override
    public int getItemCount() {
        return hotles.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTvHotleName;
        private TextView mTvHotleDesc;
        private TextView mPrice;
        private CircleImageView mUserimage;
        private ConstraintLayout mContainer;
        private ImageView mIcFavorit;


        public MyHolder(View itemView) {
            super(itemView);
            mTvHotleName = itemView.findViewById(R.id.tv_hotle_name);
            mTvHotleDesc = itemView.findViewById(R.id.tv_hotle_desc);
            mPrice = itemView.findViewById(R.id.price);
            mUserimage = itemView.findViewById(R.id.userimage);
            mContainer = itemView.findViewById(R.id.container);
            mIcFavorit = itemView.findViewById(R.id.ic_favorit);





            mIcFavorit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {


                    if (ISLOGIN) {
                    final int position = getAdapterPosition();

                    if (isFavorite) {
                        isFavorite = false;

                        mFirebaseDatabase.child(hotles.get(position).getCity_Name()).child(hotles.get(position).getId() + "").child("is_favorite").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));
                                hotles.get(position).setIs_favorite(false);
                                notifyDataSetChanged();

                            }
                        });

                    } else {
                        isFavorite = true;

                        mFirebaseDatabase.child(hotles.get(position).getCity_Name()).child(hotles.get(position).getId() + "").
                                child("is_favorite").setValue(isFavorite).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));

                                hotles.get(position).setIs_favorite(true);
                                ;
                                notifyDataSetChanged();

                            }
                        });


                    }

                    notifyDataSetChanged();


                }
                    else {

                        Toast.makeText(context, ""+context.getResources().getString(R.string.mustlogin), Toast.LENGTH_SHORT).show();
                    }


            }
            });
        }


    }

}



