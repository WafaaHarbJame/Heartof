package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.heartoftheworldapp.heartoftheworld.Activity.AdciceActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.AzkarActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.CatogoryActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.ImportantNumberActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.InformationActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.LawsActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.OfficeActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.ResturantActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.WeatherActivity;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Catogory;
import com.heartoftheworldapp.heartoftheworld.Model.HomeClass;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHolder> {

    public final List<HomeClass> catogories;
    final Context context;
    public LayoutInflater inflater;
    MyHolder holder;
    SharedPManger sharedPManger;
    String appLanguage;

    public HomeAdapter(Context context, List<HomeClass> catogories) {
        this.catogories = catogories;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.catogory_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());

        if (!(catogories.isEmpty())) {

            if (appLanguage.matches("ar")) {

                holder.catogory_name.setText(catogories.get(position).getCity_name());
                YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.catogory_photo);
                YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.catogory_name);
            } else {

                holder.catogory_name.setText(catogories.get(position).getCit_name_en());
                YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.catogory_photo);
                YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.catogory_name);

            }

            holder.catogory_photo.setImageResource(catogories.get(position).getCity_image());



        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (catogories.get(position).getId() == 0) {

                    Intent intent = new Intent(context, AzkarActivity.class);
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 1) {

                    Intent intent = new Intent(context, InformationActivity.class);
                    context.startActivity(intent);

                }

                if (catogories.get(position).getId() == 2) {

                    Intent intent = new Intent(context, WeatherActivity.class);
                    context.startActivity(intent);

                }

                if (catogories.get(position).getId() == 3) {

                    Intent intent = new Intent(context, ImportantNumberActivity.class);
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 4) {

                    Intent intent = new Intent(context, LawsActivity.class);
                    context.startActivity(intent);

                }

                if (catogories.get(position).getId() == 5) {

                    Intent intent = new Intent(context, AdciceActivity.class);
                    context.startActivity(intent);


                }
                if (catogories.get(position).getId() == 6) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
//                    Toast.makeText(context, "" + catogories.get(position).getCit_name_en(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 7) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
//                    Toast.makeText(context, "" + catogories.get(position).getCit_name_en(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 8) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

//                    Toast.makeText(context, "" + catogories.get(position).getCit_name_en(), Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 9) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 10) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 11) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 12) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 13) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 14) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 15) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 16) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

                    context.startActivity(intent);

                }
                if (catogories.get(position).getId() == 17) {

                    Intent intent = new Intent(context, CatogoryActivity.class);
                    intent.putExtra("id", catogories.get(position).getId());
                    intent.putExtra("cityname", catogories.get(position).getCit_name_en());
                    intent.putExtra("cityarname", catogories.get(position).getCity_name());

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



