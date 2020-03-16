package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Azkar;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.List;

public class AzkarAdapter extends RecyclerView.Adapter<AzkarAdapter.MyHolder> {

    MyHolder holder;

    private final List<Azkar> azkars;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean mFavorite = true;
    public Boolean isFavorite;
    private DatabaseReference mFirebaseDatabase;
    boolean ISLOGIN;


    public AzkarAdapter(Context context, List<Azkar> azkars) {
        this.azkars = azkars;
        this.context = context;
        this.inflater = LayoutInflater.from(context);



    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.azkaritem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage = sharedPManger.getDataString(AppConstants.LANG_choose, "ar");
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Offices");
        ISLOGIN = sharedPManger.getDataBool(AppConstants.ISLOGIN, false);

        if (!(azkars.isEmpty())) {

            holder.mAzkartext.setText(azkars.get(position).getAzkar_text_ar_name());
            holder.mAzkartitle.setText(azkars.get(position).getAzkar_ar_name_title());



            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mAzkartext);

        }


    }


    @Override
    public int getItemCount() {
        return azkars.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mAzkartitle;
        private TextView mAzkartext;



        public MyHolder(View itemView) {
            super(itemView);
            mAzkartitle = itemView.findViewById(R.id.azkartitle);
            mAzkartext = itemView.findViewById(R.id.azkartext);

        }


    }

}



