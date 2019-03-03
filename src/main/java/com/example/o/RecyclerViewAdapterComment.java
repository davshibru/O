package com.example.o;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

public class RecyclerViewAdapterComment extends RecyclerView.Adapter<RecyclerViewAdapterComment.ViewHolder> {


    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mUser = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<String> mComment = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterComment(ArrayList<String> mImage, ArrayList<String> mUser, ArrayList<String> mEmail, ArrayList<String> mComment, Context mContext) {
        this.mImage = mImage;
        this.mUser = mUser;
        this.mEmail = mEmail;
        this.mComment = mComment;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_listitem,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(mImage.get(i), color);

        viewHolder.image.setImageDrawable(drawable);

        viewHolder.nameOfUser.setText(mUser.get(i));
        viewHolder.emailOfUser.setText(mEmail.get(i));
        viewHolder.commentOfUser.setText(mComment.get(i));

    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView nameOfUser;
        TextView emailOfUser;
        TextView commentOfUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.userImage);
            nameOfUser = itemView.findViewById(R.id.userName);
            emailOfUser = itemView.findViewById(R.id.email);
            commentOfUser = itemView.findViewById(R.id.comentOfUsers);
        }
    }
}
