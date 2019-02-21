package com.example.o;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textView;
    final int[] mass = createMassiv();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        textView= (TextView) findViewById(R.id.textble);

        getPost();
        getComments();
    }
    private void getPost(){
        Call<List<Post>> call = jsonPlaceHolderApi.getPost(mass);

        call.enqueue(new Callback<List<Post>>(){
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for (Post post : posts) {
                    String text = "";
                    text += "UsId - " + post.getUserId() + "\n";
                    text += "Id - " + post.getId() + "\n";
                    text += "Title - " + post.getTitle() + "\n";
                    text += "Body - " + post.getBody() + "\n\n";

                    textView.append(text);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }

    private void getComments(){
        Call<List<Comments>> call = jsonPlaceHolderApi.getComm(mass);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                List<Comments> comments = response.body();
                for (Comments comm : comments){
                    String text = "";
                    text += "postId - " + comm.getPostId() + "\n";
                    text += "id - " + comm.getId() + "\n";
                    text += "name - " + comm.getName() + "\n";
                    text += "email - " + comm.getEmail() + "\n";
                    text += "body - " + comm.getBody() + "\n\n";

                    textView.append(text);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });
    }


    public int[] createMassiv(){
        int[] m = new int[10];
        for (int i = 0; i < 10; i++){
            m[i] = (int)(Math.random() * 100);
        }
        return m;
    }
}
