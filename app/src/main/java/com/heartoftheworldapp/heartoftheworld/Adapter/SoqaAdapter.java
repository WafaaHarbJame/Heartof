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
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SoqaAdapter extends RecyclerView.Adapter<SoqaAdapter.MyHolder> {

    MyHolder holder;

    private final List<Alsoaq> alsoaqs;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;


    public SoqaAdapter(Context context, List<Alsoaq> alsoaqs) {
        this.alsoaqs = alsoaqs;
        this.context = context;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.soqaitem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");

        if (!(alsoaqs.isEmpty())) {

            if (appLanguage.matches("ar")) {
                holder.mTvSoaqName.setText(alsoaqs.get(position).getSoaq_ar_name());
                holder.mTvSoaqDesc.setText(alsoaqs.get(position).getSoaq__ar_desc());
                if(alsoaqs.get(position).getSoaq_image()!=null){
                    Picasso.with(context).load(alsoaqs.get(position).getSoaq_image())
                            .error(R.drawable.logoo)
                            .into(holder.mUserimage);
                }
            } else if (appLanguage.matches("en")) {
                holder.mTvSoaqName.setText(alsoaqs.get(position).getSoaq_ar_name());
                holder.mTvSoaqDesc.setText(alsoaqs.get(position).getSoaq__ar_desc());
                if(alsoaqs.get(position).getSoaq_image()!=null){
                    Picasso.with(context).load(alsoaqs.get(position).getSoaq_image())
                            .error(R.drawable.logoo)
                            .into(holder.mUserimage);
                }



            }


            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mUserimage);

        }


    }


    @Override
    public int getItemCount() {
        return alsoaqs.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTvSoaqName;
        private TextView mTvSoaqDesc;
        private TextView mPrice;
        private CircleImageView mUserimage;
        private ConstraintLayout mContainer;

        public MyHolder(View itemView) {
            super(itemView);
            mTvSoaqName = itemView.findViewById(R.id.tv_soaq_name);
            mTvSoaqDesc = itemView.findViewById(R.id.tv_soaq_desc);
            mPrice = itemView.findViewById(R.id.price);
            mUserimage = itemView.findViewById(R.id.userimage);
            mContainer = itemView.findViewById(R.id.container);
        }


    }

}



