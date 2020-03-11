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
import com.heartoftheworldapp.heartoftheworld.Activity.CommentActivityActivity;
import com.heartoftheworldapp.heartoftheworld.Activity.MilestoneDetailsActivity;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Milestonse;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MilestonesAdapter extends RecyclerView.Adapter<MilestonesAdapter.MyHolder> {

    MyHolder holder;

    private final List<Milestonse> milestonses;
    final Context context;
    private LayoutInflater inflater;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean mFavorite=true;
    Boolean isFavorite;
    boolean ISLOGIN;

    private DatabaseReference mFirebaseDatabase;


    public MilestonesAdapter(Context context, List<Milestonse> milestonses) {
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
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Milestones");
        ISLOGIN= sharedPManger.getDataBool(AppConstants.ISLOGIN,false);

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


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MilestoneDetailsActivity.class);
                    intent.putExtra("comment_type", 2);
                    intent.putExtra("Milestonse__en__desc", milestonses.get(position).getMilestonse__en__desc());
                    intent.putExtra("Milestonse_image", milestonses.get(position).getMilestonse_image());
                    intent.putExtra("Milestonse__ar_desc", milestonses.get(position).getMilestonse__ar_desc());
                    intent.putExtra("Milestonse_id", milestonses.get(position).getId());
                    intent.putExtra("City_Name", milestonses.get(position).getCity_Name());

                    context.startActivity(intent);

                }
            });

            YoYo.with(Techniques.SlideInUp).duration(700).playOn(holder.mUserimage);

        }


    }


    @Override
    public int getItemCount() {
        return milestonses.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
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



