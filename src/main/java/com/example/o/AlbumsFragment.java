package com.example.o;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class AlbumsFragment extends Fragment {

    List<AlbumExsempels> lstAlbum;

    public static AlbumsFragment newInstance(){
        AlbumsFragment fragment = new AlbumsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstAlbum = ((MainActivity) getActivity()).getAlbums();
        ((MainActivity) getActivity()).getPost();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        RecyclerView rec = (RecyclerView) view.findViewById(R.id.recycleViewAlbum);
        RecycleViewAdapterAlbum mAdapter = new RecycleViewAdapterAlbum(getContext(),lstAlbum);
        rec.setLayoutManager(new GridLayoutManager(getContext(),3));
        rec.setAdapter(mAdapter);

        return view;
    }
}
