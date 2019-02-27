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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private RecyclerView myrecyclerView;
    private static List<PostsExempels> lstPost;
    public static String massTitle = "S";
    public static String massText = "D";
    private static ArrayList<String> mUser = new ArrayList<>();
    private static ArrayList<String> mEmail = new ArrayList<>();
    private static ArrayList<String> mComment = new ArrayList<>();

    public static PostFragment newInstance(ArrayList<PostsExempels> list){
        lstPost = list;
        PostFragment fragment = new PostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        myrecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(), lstPost);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(recycleViewAdapter);

        return view;
    }

}