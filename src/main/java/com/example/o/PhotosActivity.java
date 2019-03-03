package com.example.o;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<PhotoExempels> lstPhoto;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ID = Integer.parseInt(getIntent().getStringExtra("id"));
        lstPhoto = new ArrayList<>();

        try {
            FileInputStream fileImput = openFileInput("photos.txt");
            InputStreamReader reader = new InputStreamReader(fileImput);
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            String id = "";
            String photo = "";
            String name = "";
            String big = "";

            while ((line = buffer.readLine()) != null) {
                if (line.equals(ID + "")) {
                    id = line;
                    line = buffer.readLine();
                    photo = line;
                    line = buffer.readLine();
                    name = line;
                    line = buffer.readLine();
                    big = line;
                    Log.e("vvvvvvvvvvvvvvvv", name);

                    lstPhoto.add(new PhotoExempels(id, photo, name, big));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewPhoto);
        RecyclerViewAdapterPhoto adapterPhoto = new RecyclerViewAdapterPhoto(this, lstPhoto);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapterPhoto);
        Log.e("Res", "Dddddddddddddddd");

    }
}

