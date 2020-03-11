package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.heartoftheworldapp.heartoftheworld.Activity.AboutCityActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.AddSoqaaActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.HotleActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.MilestoneActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.OfficeActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.ResturantActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.SoqaaActivity;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.Model.Hotles;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CatogoryAdapter extends RecyclerView.Adapter<CatogoryAdapter.MyHolder> {

    MyHolder holder;

    private final List<Catogory> catogories;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;

    public CatogoryAdapter(Context context, List<Catogory> catogories){
        this.catogories = catogories;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.catogory_item,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger=new SharedPManger(context);
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if (!(catogories.isEmpty())) {

            holder.catogory_name.setText(catogories.get(position).getCatogory_name());
           Picasso.with(context).load(catogories.get(position).getCatogory_image())
                   .error(R.drawable.logoo)
                   .placeholder(R.drawable.raidaa)
                   .into(holder.catogory_photo);
       YoYo.with(Techniques.SlideInUp)
                    .duration(700)
                    .playOn(holder.catogory_photo);
            YoYo.with(Techniques.SlideInUp)
                    .duration(700)
                    .playOn(holder.catogory_name);

        }


     holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(catogories.get(position).getId()==1){

                    Intent intent=new Intent(context, ResturantActivity.class);
                    intent.putExtra("cityname",catogories.get(position).getCity());
                    context.startActivity(intent);

                }

                if(catogories.get(position).getId()==4){

                    Intent intent=new Intent(context, OfficeActivity.class);
                    intent.putExtra("cityname",catogories.get(position).getCity());

                    context.startActivity(intent);

                }


                if(catogories.get(position).getId()==3){

                    Intent intent=new Intent(context, MilestoneActivity.class);
                    intent.putExtra("cityname",catogories.get(position).getCity());

                    context.startActivity(intent);

                }

                if(catogories.get(position).getId()==6){

                    Intent intent=new Intent(context, SoqaaActivity.class);
                    intent.putExtra("cityname",catogories.get(position).getCity());

                    context.startActivity(intent);

                }

                if(catogories.get(position).getId()==2){

                    Intent intent=new Intent(context, HotleActivity.class);
                    intent.putExtra("cityname",catogories.get(position).getCity());

                    context.startActivity(intent);

                }
                if(catogories.get(position).getId()==5){

                    Intent intent=new Intent(context, AboutCityActivity.class);
                    intent.putExtra("cityname",catogories.get(position).getCity());

                    context.startActivity(intent);

                }


            }
        });
    }


    @Override
    public int getItemCount() {
        return catogories.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView catogory_name;
        ImageView catogory_photo;
      RelativeLayout cardView;

        public MyHolder(View itemView) {
            super(itemView);
            catogory_name = itemView.findViewById(R.id.catogory_name);
            catogory_photo = itemView.findViewById(R.id.catogory_photo);
            cardView = itemView.findViewById(R.id.cardView);


        }


        }

    }



