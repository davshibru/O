package com.example.o;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mUser = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<String> mComment = new ArrayList<>();
    TextView mTitle;
    TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_of_post);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

            String title = getIntent().getStringExtra("title");
            String text = getIntent().getStringExtra("text");

            setText(title, text);
    }

    private void setText(String title, String text){

        mTitle = (TextView) findViewById(R.id.titleOfPost);
        mText = (TextView) findViewById(R.id.textOfPost);

        mTitle.setText(title);
        mText.setText(text);

        setRecyclerView();


    }

    private void setRecyclerView(){

        mImage.add("A");
        mUser.add("Dimenter Dimenter Dimenter Dimenter Dimenter");
        mEmail.add("shibru_@gmail.kg");
        mComment.add("its not good its not good its not good its not good v its not good its not good its not good");

        mImage.add("D");
        mUser.add("Dimenter Dimenter Dimenter Dimenter Dimenter");
        mEmail.add("shibru_@gmail.kg");
        mComment.add("its not good its not good its not good its not good v its not good its not good its not good");

        mImage.add("C");
        mUser.add("Dimenter Dimenter Dimenter Dimenter Dimenter");
        mEmail.add("shibru_@gmail.kg");
        mComment.add("its not good its not good its not good its not good v its not good its not good its not good");

        mImage.add("B");
        mUser.add("Dimenter Dimenter Dimenter Dimenter Dimenter");
        mEmail.add("shibru_@gmail.kg");
        mComment.add("its not good its not good its not good its not good v its not good its not good its not good");

        doIt();
    }

    private void doIt(){
        RecyclerView recyclerView = findViewById(R.id.comment_recycleview);
        RecyclerViewAdapterComment adapterComment = new RecyclerViewAdapterComment(mImage,mUser,mEmail,mComment,this);
        recyclerView.setAdapter(adapterComment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
