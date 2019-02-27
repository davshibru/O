package com.example.o;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {

    List<PhotoExempels> lstPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("NameOfPic");

        lstPhoto = new ArrayList<>();
        lstPhoto.add(new PhotoExempels("https://avatars.mds.yandex.net/get-pdb/1592033/dc585aab-cf1d-4144-b004-950612f80727/s1200?webp=false",name));
        lstPhoto.add(new PhotoExempels("https://w-dog.ru/wallpapers/11/18/443734993463506.jpg", "flawers"));
        lstPhoto.add(new PhotoExempels("https://images.wallpaperscraft.ru/image/kot_pushistyy_zabor_sidet_osheynik_59738_1920x1080.jpg","cat"));
        lstPhoto.add(new PhotoExempels("https://avatars.mds.yandex.net/get-pdb/1592033/dc585aab-cf1d-4144-b004-950612f80727/s1200?webp=false","big cat"));
        lstPhoto.add(new PhotoExempels("https://www.nastol.com.ua/download.php?img=201306/1440x900/nastol.com.ua-50012.jpg","apple"));
        lstPhoto.add(new PhotoExempels("https://w-dog.ru/wallpapers/11/18/443734993463506.jpg", "flawers"));
        lstPhoto.add(new PhotoExempels("https://images.wallpaperscraft.ru/image/kot_pushistyy_zabor_sidet_osheynik_59738_1920x1080.jpg","cat"));
        lstPhoto.add(new PhotoExempels("https://avatars.mds.yandex.net/get-pdb/1592033/dc585aab-cf1d-4144-b004-950612f80727/s1200?webp=false","big cat"));
        lstPhoto.add(new PhotoExempels("https://www.nastol.com.ua/download.php?img=201306/1440x900/nastol.com.ua-50012.jpg","apple"));
        lstPhoto.add(new PhotoExempels("https://w-dog.ru/wallpapers/11/18/443734993463506.jpg", "flawers"));
        lstPhoto.add(new PhotoExempels("https://images.wallpaperscraft.ru/image/kot_pushistyy_zabor_sidet_osheynik_59738_1920x1080.jpg","cat"));
        lstPhoto.add(new PhotoExempels("https://avatars.mds.yandex.net/get-pdb/1592033/dc585aab-cf1d-4144-b004-950612f80727/s1200?webp=false","big cat"));
        lstPhoto.add(new PhotoExempels("https://www.nastol.com.ua/download.php?img=201306/1440x900/nastol.com.ua-50012.jpg","apple"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewPhoto);
        RecyclerViewAdapterPhoto adapterPhoto = new RecyclerViewAdapterPhoto(this,lstPhoto);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapterPhoto);

    }
}
