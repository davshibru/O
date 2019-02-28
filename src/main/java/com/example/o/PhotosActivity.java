package com.example.o;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {

    List<PhotoExempels> lstPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        String ID = getIntent().getStringExtra("id");


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
                if (line.equals(ID)) {
                    id = line;
                    line = buffer.readLine();
                    photo = line;
                    line = buffer.readLine();
                    name = line;
                    line = buffer.readLine();
                    big = line;
                    lstPhoto.add(new PhotoExempels(id, photo, name, big));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleViewPhoto);
        RecyclerViewAdapterPhoto adapterPhoto = new RecyclerViewAdapterPhoto(this,lstPhoto);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(adapterPhoto);

    }
}
