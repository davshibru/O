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
    private List<PostsExempels> lstPost;
    public static String massTitle = "S";
    public static String massText = "D";

    public static PostFragment newInstance(String mTitle, String mText){
        massTitle = mTitle;
        massText = mText;
        PostFragment fragment = new PostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        lstPost = new ArrayList<>();

//        getPost();

        String dav = massTitle;
        String tex = massText;
        lstPost.add(new PostsExempels(dav, tex));

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

//    private void getPost(){
//        Call<List<Post>> call = jsonPlaceHolderApi.getPost(createMassiv());
//        massTitle = "dav";
//        massText = "dav";
//        call.enqueue(new Callback<List<Post>>(){
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                massTitle ="David";
//                massText = "kross";
//                List<Post> posts = response.body();
//                for (Post post : posts) {
//                    massTitle = "devinf";
//                    massText = "adwddddddd";
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                massTitle = t.toString();
//                massText = toString();
//            }
//        });
//    }

    public int[] createMassiv(){
        int[] m = new int[1];
        for (int i = 0; i < 10; i++){
            m[i] = (int)(Math.random() * 100);
        }
        return m;
    }

}
