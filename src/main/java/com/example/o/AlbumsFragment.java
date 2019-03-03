package com.example.o;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AlbumsFragment extends Fragment {

    RecyclerView rec;
    List<AlbumExsempels> lstAlbum;
    int[] mass = createMassiv(10,100);

    public AlbumsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstAlbum = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        rec = (RecyclerView) view.findViewById(R.id.recycleViewAlbum);

        ((MainActivity)getActivity()).getPhotos(mass);

        getAlbums();

        return view;
    }

    public void getAlbums(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Albums>> call = jsonPlaceHolderApi.getAlbums(mass);

        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                List<Albums> albums = response.body();
                for (Albums album : albums){
                    lstAlbum.add(new AlbumExsempels(album.getTitle(),album.getId() + ""));
                }
                RecycleViewAdapterAlbum mAdapter = new RecycleViewAdapterAlbum(getContext(),lstAlbum);
                rec.setLayoutManager(new GridLayoutManager(getContext(),3));
                rec.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
            }
        });
    }


    public int[] createMassiv(int ten, int diopazon){
        int[] m = new int[ten];
        for (int i = 0; i < ten; i++){
            m[i] = (int)(Math.random() * diopazon);
        }
        return m;
    }


}
