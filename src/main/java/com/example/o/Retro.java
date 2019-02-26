package com.example.o;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    private static String title;
    private String text = "t";
    private static String[] str ;
    private static Retro post;
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Retro(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPost(createMassiv());

        call.enqueue(new Callback<List<Post>>(){
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                int i = 0;
                for (Post post : posts) {
                    title = post.getTitle();
                    text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaadddd";
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }

//        str = new String[]{title,text};
//        System.out.print(str[0] + " " + str[1]);

    public static String getPostFrom(){
        if (title.equals("")){
            new Retro();
        }
        return title;
    }




    public int[] createMassiv(){
        int[] m = new int[10];
        for (int i = 0; i < 10; i++){
            m[i] = (int)(Math.random() * 100);
        }
        return m;
    }
}
