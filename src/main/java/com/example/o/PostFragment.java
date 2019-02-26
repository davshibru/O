package com.example.o;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {

    private RecyclerView myrecyclerView;
    private List<PostsExempels> lstPost;

    public static PostFragment newInstance(){
        PostFragment fragment = new PostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstPost = new ArrayList<>();

        lstPost.add(new PostsExempels("David","Good man"));
        lstPost.add(new PostsExempels("Vera","Good girl"));
        lstPost.add(new PostsExempels("Shibru","Cool family"));
        lstPost.add(new PostsExempels("David","Good man"));
        lstPost.add(new PostsExempels("Vera","Good girl"));
        lstPost.add(new PostsExempels("Shibru","Cool family"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post,container,false);
        myrecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(),lstPost);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(recycleViewAdapter);

        return view;
    }

}
