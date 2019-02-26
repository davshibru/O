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

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

public class RecycleViewAdapterAlbum extends RecyclerView.Adapter<RecycleViewAdapterAlbum.MyHolder> {

    private Context mContext;
    private List<AlbumExsempels> mData;

    public RecycleViewAdapterAlbum(Context mContext, List<AlbumExsempels> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mInfliter = LayoutInflater.from(mContext);
        view = mInfliter.inflate(R.layout.cardview_item_album,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        myHolder.tv_nameOfAlbium.setText(mData.get(i).getNameOfAlb());
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(mData.get(i).getFiresLater(), color);
        myHolder.imgOfAlbum.setImageDrawable(drawable);

        myHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,PhotosActivity.class);
                intent.putExtra("NameOfPic", mData.get(i).getNameOfAlb());//передай айди альбома для фото
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv_nameOfAlbium;
        ImageView imgOfAlbum;
        CardView cardView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardViewIdAlbum);
            tv_nameOfAlbium = (TextView) itemView.findViewById(R.id.textOfPic);
            imgOfAlbum = (ImageView) itemView.findViewById(R.id.pic);
        }
    }
}
