package com.example.o;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    Context mContext;
    List<PostsExempels> mData;



    public RecycleViewAdapter(Context mContext, List<PostsExempels> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.post_item_fragment,viewGroup,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_title.setText(mData.get(i).getTitle());
        myViewHolder.tv_text.setText(mData.get(i).getText());
        final String title = mData.get(i).getTitle();
        final String text = mData.get(i).getText();
        final String id = "" + mData.get(i).getId();

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(mData.get(i).getFirstLater(), color);
        myViewHolder.img.setImageDrawable(drawable);

        myViewHolder.item_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommentActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("text",text);
                intent.putExtra("id",id);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout item_post;
        private TextView tv_title;
        private TextView tv_text;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_post =(RelativeLayout) itemView.findViewById(R.id.parent_layout);
            tv_title = (TextView) itemView.findViewById(R.id.title);
            tv_text = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.image);



        }
    }
}
