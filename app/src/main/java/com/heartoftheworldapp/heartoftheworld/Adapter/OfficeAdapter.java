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
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Offices;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.List;
import java.util.Locale;

public class OfficeAdapter extends RecyclerView.Adapter<OfficeAdapter.MyHolder> {

    MyHolder holder;

    private final List<Offices> offices;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean mFavorite=true;
    public Boolean isFavorite;
    private DatabaseReference mFirebaseDatabase;
    boolean ISLOGIN;


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
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Offices");
        ISLOGIN= sharedPManger.getDataBool(AppConstants.ISLOGIN,false);

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

            isFavorite = offices.get(position).isIs_favorite();

            if (isFavorite) {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
            } else {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));

            }
            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mOfficename);

        }


    }


    @Override
    public int getItemCount() {
        return offices.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mOfficetype;
        private TextView mOfficename;
        private TextView mOfficenumber;
        private ImageView mIcFavorit;


        public MyHolder(View itemView) {
            super(itemView);
            mOfficetype = itemView.findViewById(R.id.officetype);
            mOfficename = itemView.findViewById(R.id.officename);
            mOfficenumber = itemView.findViewById(R.id.officenumber);
            mIcFavorit = itemView.findViewById(R.id.ic_favorit);
            mIcFavorit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if(ISLOGIN) {
                        if (isFavorite) {
                            isFavorite = false;

                            mFirebaseDatabase.child(offices.get(position).getCity_Name()).
                                    child(offices.get(position).getId() + "").child("is_favorite")
                                    .setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));
                                    offices.get(position).setIs_favorite(false);
                                    notifyDataSetChanged();

                                }
                            });

                        } else {
                            isFavorite = true;

                            mFirebaseDatabase.child(offices.get(position).getCity_Name()).child(offices.get(position).getId() + "").
                                    child("is_favorite").setValue(isFavorite).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));

                                    offices.get(position).setIs_favorite(true);
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



