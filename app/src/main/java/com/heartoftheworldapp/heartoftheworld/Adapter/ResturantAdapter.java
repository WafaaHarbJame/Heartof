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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heartoftheworldapp.heartoftheworld.Activity.LocationActivity;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Resturants;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.List;
import java.util.Locale;

public class ResturantAdapter extends RecyclerView.Adapter<ResturantAdapter.MyHolder> {

    private final List<Resturants> resturants;
    public Context context;
    MyHolder holder;
    SharedPManger sharedPManger;
    String appLanguage;
    Boolean isFavorite, mFavorite;
    boolean ISLOGIN;
    String KEY_PHONE;
    private LayoutInflater inflater;
    private TextView mResturantsName;
    private DatabaseReference mFirebaseDatabase, mFirebaseFavDatabase;

    public ResturantAdapter(Context context, List<Resturants> resturants) {
        this.resturants = resturants;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.resturant_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;
        sharedPManger = new SharedPManger(context);
        appLanguage=  sharedPManger.getDataString(AppConstants.LANG_choose, Locale.getDefault().getLanguage());
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Resturants");
        mFirebaseFavDatabase = FirebaseDatabase.getInstance().getReference("Favorites");

        ISLOGIN = sharedPManger.getDataBool(AppConstants.ISLOGIN, false);
        KEY_PHONE = sharedPManger.getDataString(AppConstants.KEY_PHONE);
        if (!(resturants.isEmpty())) {

            if (appLanguage.matches("ar")) {
                holder.mResturantsName.setText(resturants.get(position).getResturants_ar_name());
                if (resturants.get(position).getResturants_ar_address() == null) {
                    holder.mResturantsAddress.setVisibility(View.GONE);
                } else {
                    holder.mResturantsAddress.setText(resturants.get(position).getResturants_ar_address());

                }

                if (resturants.get(position).getResturants_phone() == null) {
                    holder.mResturantsPhone.setVisibility(View.GONE);
                } else {
                    holder.mResturantsPhone.setText(resturants.get(position).getResturants_phone());

                }
                holder.mResturantsDesc.setText(resturants.get(position).getResturants_ar_desc());

            } else if (appLanguage.matches("en")) {
                holder.mResturantsName.setText(resturants.get(position).getResturants_en_name());

                if (resturants.get(position).getResturants_en_address() == null) {
                    holder.mResturantsAddress.setVisibility(View.GONE);
                } else {
                    holder.mResturantsAddress.setText(resturants.get(position).getResturants_en_address());

                }

                if (resturants.get(position).getResturants_phone() == null) {
                    holder.mResturantsPhone.setVisibility(View.GONE);
                } else {
                    holder.mResturantsPhone.setText(resturants.get(position).getResturants_phone());

                }
                holder.mResturantsDesc.setText(resturants.get(position).getResturants_en__desc());


            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, LocationActivity.class);
                    intent.putExtra("Resturants_link", resturants.get(position).getResturants_link());
                    intent.putExtra("Resturants_ar_name", resturants.get(position).getResturants_ar_name());
                    intent.putExtra("Resturants_en_name", resturants.get(position).getResturants_en_name());
                    intent.putExtra("Resturants_ar_desc", resturants.get(position).getResturants_ar_desc());
                    intent.putExtra("Resturants_en__desc", resturants.get(position).getResturants_en__desc());
                    intent.putExtra("City_Name", resturants.get(position).getCity_Name());
                    intent.putExtra("id", resturants.get(position).getId());
                    intent.putExtra("Resturants_en_address", resturants.get(position).getResturants_en_address());
                    intent.putExtra("Resturants_ar_address", resturants.get(position).getResturants_ar_address());
                    intent.putExtra("Resturants_phone", resturants.get(position).getResturants_phone());


                    context.startActivity(intent);
                }
            });

            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mResturantsName);

            isFavorite = resturants.get(position).isIs_favorite();

            if (isFavorite) {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
            } else {
                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));

            }














//            holder.mIcFavorit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (isFavorite) {
//                        isFavorite = false;
//                        mFirebaseDatabase.child(resturants.get(position).getCity_Name())
//                                .child(resturants.get(position).getId() + "")
//                                .child("is_favorite").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));
//
//                                resturants.get(position).setIs_favorite(false);
//
//                            }
//                        });
//
//
//
//
//                    }
//
//                    else {
//                        isFavorite = true;
//
//                        mFirebaseDatabase.child(resturants.get(position).getCity_Name())
//                                .child(resturants.get(position).getId() + "").
//                                child("is_favorite").setValue(isFavorite).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
//
//                                resturants.get(position).setIs_favorite(true);;
//                            }
//                        });
//
//
//                    }
//
//
//                }
//            });


        }


    }

    @Override
    public int getItemCount() {
        return resturants.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mResturantsName;
        private TextView mResturantsDesc;
        private TextView mResturantsPhone;
        private TextView mResturantsAddress;
        private TextView mResturantsLink;
        private ImageView mIcFavorit;

        public MyHolder(View itemView) {
            super(itemView);
            mResturantsName = itemView.findViewById(R.id.Resturants_name);
            mResturantsDesc = itemView.findViewById(R.id.Resturants_desc);
            mResturantsPhone = itemView.findViewById(R.id.Resturants_phone);
            mResturantsAddress = itemView.findViewById(R.id.Resturants_address);
            mResturantsLink = itemView.findViewById(R.id.Resturants_link);
            mIcFavorit = itemView.findViewById(R.id.ic_favorit);











//            holder.mIcFavorit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    final int position = getAdapterPosition();
//
//                    final Resturants resturantsfav = new Resturants(resturants.get(position).getId(),
//                            resturants.get(position).getResturants_ar_name(),
//                            resturants.get(position).getResturants_en_name(),
//                            resturants.get(position).getResturants_ar_desc(),
//                            resturants.get(position).getResturants_en__desc(),
//                            resturants.get(position).getResturants_link(),
//                            resturants.get(position).getResturants_phone(),
//                            resturants.get(position).getResturants_ar_address(),
//                            resturants.get(position).getResturants_en_address(),
//                            true,resturants.get(position).getCity_Name());
//
//                    if (ISLOGIN) {
//
//                        if (!isFavorite) {
//
//
//                            isFavorite = true;
//                            mFirebaseFavDatabase.child(KEY_PHONE)
//                                    .child("FavoriteResturant"). child(resturants.get(position).getId()+"")
//                                    .addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                            if(dataSnapshot.exists()) {
//                                                mFirebaseFavDatabase.child(KEY_PHONE)
//                                                        .child("FavoriteResturant"). child(resturants.get(position).getId()+"")
//                                                        .setValue(resturantsfav);
//                                                holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
//                                                resturants.get(position).setIs_favorite(true);;
//                                                notifyDataSetChanged();
//
//                                            }
//
//                                            else
//                                            {
//
//                                                mFirebaseFavDatabase.child(KEY_PHONE)
//                                                        .child("FavoriteResturant").
//                                                        child(resturants.get(position).getId()+"")
//                                                        .setValue(resturantsfav).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                        holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
//                                                        resturants.get(position).setIs_favorite(true);;
//                                                        notifyDataSetChanged();
//                                                    }
//                                                });
//
//
//                                            }
//
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                        }
//                                    });
//
//                        }
//
//                        else {
//
//                            isFavorite = false;
//                            mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteResturant")
//                                    .child(resturants.get(position).getId()+"").child("is_favorite")
//                                    .setValue(false);
//                            holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));
//                            resturants.get(position).setIs_favorite(false);;
//                            notifyDataSetChanged();
//
//
//                        }
//
//                    } else {
//
//
//                        Toast.makeText(context, "" + context.getResources().getString(R.string.mustlogin), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });


//            mIcFavorit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    final int position = getAdapterPosition();
//                    final Resturants resturantsfav = new Resturants(resturants.get(position).getId(),
//                            resturants.get(position).getResturants_ar_name(),
//                            resturants.get(position).getResturants_en_name(),
//                            resturants.get(position).getResturants_ar_desc(),
//                            resturants.get(position).getResturants_en__desc(),
//                            resturants.get(position).getResturants_link(),
//                            resturants.get(position).getResturants_phone(),
//                            resturants.get(position).getResturants_ar_address(),
//                            resturants.get(position).getResturants_en_address(),
//                            true,resturants.get(position).getCity_Name());
//
//                    if (ISLOGIN) {
//
//                        if (!isFavorite) {
//
//
//                            isFavorite = true;
//                            mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteResturant").child(resturants.get(position).getId()+"").addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                    mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteResturant").
//                                            child(resturants.get(position).getId()+""+"").setValue(resturantsfav);
//                                    holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoritefull));
//                                    resturants.get(position).setIs_favorite(true);;
//                                    notifyDataSetChanged();
//
//                                }
//
//                                @Override
//                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                }
//                            });
//
//                        }
//
//                        else {
//
//                            isFavorite = false;
//                            mFirebaseFavDatabase.child(KEY_PHONE).child("FavoriteResturant")
//                                    .child(resturants.get(position).getId()+""+"").child("is_favorite")
//                                    .setValue(false);
//                            holder.mIcFavorit.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favoriteempty));
//                            resturants.get(position).setIs_favorite(false);;
//                            notifyDataSetChanged();
//
//
//                        }
//
//                    } else {
//
//
//                        Toast.makeText(context, "" + context.getResources().getString(R.string.mustlogin), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

        }
    }
}
