package com.example.o;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item_fragment, viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).binView(i);
    }

    @Override
    public int getItemCount() {
        return PostsExempels.title.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageView;
        private TextView mTitle;
        private TextView mText;

        public ListViewHolder(View itemView){

            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mText = (TextView) itemView.findViewById(R.id.text);
            mImageView = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        public void binView(int position){
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getRandomColor();

            TextDrawable drawable = TextDrawable.builder().buildRound("A", color);
            mImageView.setImageDrawable(drawable);
            mTitle.setText(PostsExempels.title[position]);
            mText.setText(PostsExempels.text[position]);

        }

        public void onClick(View v){

        }






    }


//    private static final String TAG = "RecyclerViewAdapter";
//
//    private ArrayList<String> mTitle = new ArrayList<>();
//    private ArrayList<String> mText = new ArrayList<>();
//    private Context mContext;
//
//    public RecycleViewAdapter(ArrayList<String> mTitle, ArrayList<String> mText, Context mContext) {
//        this.mTitle = mTitle;
//        this.mText = mText;
//        this.mContext = mContext;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item_fragment,viewGroup,false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        ColorGenerator generator = ColorGenerator.MATERIAL;
//        int color = generator.getRandomColor();
//
//        TextDrawable drawable = TextDrawable.builder().buildRound("A", color);
//        viewHolder.image.setImageDrawable(drawable);
////        Glide.with(mContext)
////                .asBitmap()
////                .load(mImage.get(i))
////                .into(viewHolder.image);
//
//        viewHolder.titel.setText(mTitle.get(i));
//        viewHolder.text.setText(mText.get(i));
//    }
//
//    @Override
//    public int getItemCount() {
//        return mTitle.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView image;
//        TextView titel;
//        TextView text;
//        RelativeLayout parentLayout;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            image = itemView.findViewById(R.id.image);
//            titel = itemView.findViewById(R.id.title);
//            text = itemView.findViewById(R.id.text);
//            parentLayout = itemView.findViewById(R.id.parent_layout);
//        }
//    }
}
