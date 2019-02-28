package com.example.o;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

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

public class CommentActivity extends AppCompatActivity {

    private ArrayList<Integer> mId = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mUser = new ArrayList<>();
    private ArrayList<String> mEmail = new ArrayList<>();
    private ArrayList<String> mComment = new ArrayList<>();
    TextView mTitle;
    TextView mText;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    private String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_of_post);


        getIncomingIntent();

        try {
            FileInputStream fileImput = openFileInput("comment.txt");
            InputStreamReader reader = new InputStreamReader(fileImput);
            BufferedReader buffer = new BufferedReader(reader);
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line.equals(id)) {
                    line = buffer.readLine();
                    mUser.add((line));
                    line = buffer.readLine();
                    String s = "";
                    while (true) {
                        if (("" + line.charAt(0)).equals("]")) {
                            line = line.substring(1);
                            break;
                        } else {
                            s += line;
                            line = buffer.readLine();
                        }
                    }
                    mComment.add(s);
                    mImage.add(line);
                    line = buffer.readLine();
                    mEmail.add(line);
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    private void getIncomingIntent(){

            String title = getIntent().getStringExtra("title");
            String text = getIntent().getStringExtra("text");
            id = getIntent().getStringExtra("id");
            setText(title, text);
    }

    private void setText(String title, String text){

        mTitle = (TextView) findViewById(R.id.titleOfPost);
        mText = (TextView) findViewById(R.id.textOfPost);

        mTitle.setText(title);
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
