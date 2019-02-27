package com.example.o;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentActivity extends AppCompatActivity {

    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mUser = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<String> mComment = new ArrayList<>();
    TextView mTitle;
    TextView mText;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private int Id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_of_post);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getComment();

        getIncomingIntent();




    }

    private void getComment(){
            Call<List<Comments>> call = jsonPlaceHolderApi.getComm(Id);

            call.enqueue(new Callback<List<Comments>>() {
                @Override
                public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                    List<Comments> comments = response.body();
                    for (Comments comm : comments){
                        mUser.add(comm.getName());
                        mEmail.add(comm.getEmail());
                        mComment.add(comm.getBody());
                    }
                }

                @Override
                public void onFailure(Call<List<Comments>> call, Throwable t) {

                }
            });
        }

    private void getIncomingIntent(){

            String title = getIntent().getStringExtra("title");
            String text = getIntent().getStringExtra("text");
            String id = getIntent().getStringExtra("id");
            Id = Integer.parseInt(id);
            setText(title, text);
    }

    private void setText(String title, String text){

        mTitle = (TextView) findViewById(R.id.titleOfPost);
        mText = (TextView) findViewById(R.id.textOfPost);

        mTitle.setText(title + Id);
        mText.setText(text);

        doIt();
    }



    private void doIt(){
        RecyclerView recyclerView = findViewById(R.id.comment_recycleview);
        RecyclerViewAdapterComment adapterComment = new RecyclerViewAdapterComment(mImage,mUser,mEmail,mComment,this);
        recyclerView.setAdapter(adapterComment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
