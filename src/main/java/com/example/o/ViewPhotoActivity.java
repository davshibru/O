package com.example.o;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ViewPhotoActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        img = (ImageView) findViewById(R.id.big_photo);

        Intent intent = getIntent();
        String photo = intent.getExtras().getString("photo");

        Glide.with(this)
                .asBitmap()
                .load(photo)
                .into(img);
    }
}
