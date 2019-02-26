package com.example.o;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_of_post);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("title")){
            String title = getIntent().getStringExtra("title");
            String text = getIntent().getStringExtra("text");

            setText(title, text);
        }
        else{
            String text = getIntent().getStringExtra("text");

            setText("not work", text);
        }
    }

    private void setText(String title, String text){
        TextView name = findViewById(R.id.tv);
        name.setText("title - " + title + "\ntext - " + text);
    }
}
