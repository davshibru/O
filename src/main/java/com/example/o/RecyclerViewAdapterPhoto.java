package com.example.o;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapterPhoto extends RecyclerView.Adapter<RecyclerViewAdapterPhoto.MtViewHolder> {

    private Context mContext;
    private List<PhotoExempels> mData;

    public RecyclerViewAdapterPhoto(Context mContext, List<PhotoExempels> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MtViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_for_photo,viewGroup,false);

        return new MtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MtViewHolder mtViewHolder, final int i) {

        String str = mData.get(i).getNameOfPhoto();
        String chr = "";
        if (str.length() > 10 ){
            for (int j = 0; j < 10; j++){
                chr += str.charAt(j);
            }
            chr += "...";
        }
        else {
            chr = str;
        }

        mtViewHolder.tv_name.setText(chr);
        Glide.with(mContext)
                .asBitmap()
                .load(mData.get(i).getPhoto())
                .into(mtViewHolder.iv_photo);
        mtViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ViewPhotoActivity.class);
                intent.putExtra("photo",mData.get(i).getBigPhoto());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MtViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_photo;
        TextView tv_name;
        CardView cardView;

        public MtViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_photo = (ImageView) itemView.findViewById(R.id.photo);
            tv_name = (TextView) itemView.findViewById(R.id.textOfPhoto);
            cardView = (CardView) itemView.findViewById(R.id.cardview_item_photo);
        }
    }
}
