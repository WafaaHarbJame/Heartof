package com.heartoftheworldapp.heartoftheworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heartoftheworldapp.heartoftheworld.Model.AppConstants;
import com.heartoftheworldapp.heartoftheworld.Model.Comments;
import com.heartoftheworldapp.heartoftheworld.Model.SharedPManger;
import com.heartoftheworldapp.heartoftheworld.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyHolder> {

    final Context context;
    private final List<Comments> comments;
    public Boolean isFavorite;
    MyHolder holder;
    SharedPManger sharedPManger;
    String appLanguage;
    boolean mFavorite = true;
    boolean ISLOGIN;
    private LayoutInflater inflater;
    private DatabaseReference mFirebaseDatabase;


    public CommentAdapter(Context context, List<Comments> comments) {
        this.comments = comments;
        this.context = context;
        this.inflater = LayoutInflater.from(context);


    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comment_item, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        this.holder = holder;

        if (!(comments.isEmpty())) {

            holder.mCommentdate.setText(comments.get(position).getComment_date());
            holder.mTvCommentText.setText(comments.get(position).getComment_text());

                holder.mTvUserName.setText(comments.get(position).getComment_username());





        }


    }


    @Override
    public int getItemCount() {
        return comments.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {


        private TextView mTvUserName;
        private TextView mTvCommentText;
        private TextView mCommentdate;
        private CircleImageView mUserimage;
        private ConstraintLayout mContainer;

        public MyHolder(View itemView) {
            super(itemView);

            mTvUserName = itemView.findViewById(R.id.tv_user_name);
            mTvCommentText = itemView.findViewById(R.id.tv_comment_text);
            mCommentdate = itemView.findViewById(R.id.commentdate);
            mUserimage = itemView.findViewById(R.id.userimage);

        }


    }

}



