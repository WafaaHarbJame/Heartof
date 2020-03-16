package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
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
import com.heartoftheworldapp.heartoftheworld.Model.Alsoaq;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class SoqaAdapter extends RecyclerView.Adapter<SoqaAdapter.MyHolder> {

    MyHolder holder;

    private final List<Alsoaq> alsoaqs;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean mFavorite=true;
    public static Boolean isFavorite;
    boolean ISLOGIN;

    private DatabaseReference mFirebaseDatabase;

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
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Alasqaa");
        ISLOGIN= sharedPManger.getDataBool(AppConstants.ISLOGIN,false);

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
                holder.mTvSoaqName.setText(alsoaqs.get(position).getSoaq_en_name());
                holder.mTvSoaqDesc.setText(alsoaqs.get(position).getSoaq__en__desc());
                if(alsoaqs.get(position).getSoaq_image()!=null){
                    Picasso.with(context).load(alsoaqs.get(position).getSoaq_image())
                            .error(R.drawable.logoo)
                            .into(holder.mUserimage);
                }



            }


            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mUserimage);
            isFavorite = alsoaqs.get(position).isIs_favorite();

            if (isFavorite) {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
            } else {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));

            }
        }


    }


    @Override
    public int getItemCount() {
        return alsoaqs.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTvSoaqName;
        private TextView mTvSoaqDesc;
        private TextView mPrice;
        private CircleImageView mUserimage;
        private ConstraintLayout mContainer;
        private ImageView mIcFavorit;


        public MyHolder(View itemView) {
            super(itemView);
            mTvSoaqName = itemView.findViewById(R.id.tv_soaq_name);
            mTvSoaqDesc = itemView.findViewById(R.id.tv_soaq_desc);
            mPrice = itemView.findViewById(R.id.price);
            mUserimage = itemView.findViewById(R.id.userimage);
            mContainer = itemView.findViewById(R.id.container);
            mIcFavorit = itemView.findViewById(R.id.ic_favorit);
            mIcFavorit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (ISLOGIN) {
                        if (isFavorite) {
                            isFavorite = false;

                            mFirebaseDatabase.child(alsoaqs.get(position).getCity_Name()).child(alsoaqs.get(position).getId() + "").child("is_favorite").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));
                                    alsoaqs.get(position).setIs_favorite(false);
                                    notifyDataSetChanged();

                                }
                            });

                        } else {
                            isFavorite = true;

                            mFirebaseDatabase.child(alsoaqs.get(position).getCity_Name()).child(alsoaqs.get(position).getId() + "").
                                    child("is_favorite").setValue(isFavorite).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));

                                    alsoaqs.get(position).setIs_favorite(true);
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



