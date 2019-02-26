package com.example.o;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//    private static String lstText;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textView;
//    final int[] mass = createMassiv();
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();
    private String lstTitle ="d";
    private String lstText = "ddddd";
    String txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = (TextView) findViewById(R.id.textView);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        getPost();
//        getComments();


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment selectedFragment = null;
                        switch (menuItem.getItemId()){
                            case R.id.PostItem:
                                selectedFragment = PostFragment.newInstance(lstTitle,lstText);
                                break;
                            case R.id.AlbumItem:
                                selectedFragment = AlbumsFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.main_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                }
        );
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, PostFragment.newInstance(lstTitle,lstText));
        transaction.commit();

//        getPost();
//        getComments();
//        getAlbums();
//        getPhotos();
//        initImageVitMaps();
    }

//    public void TextAndTitle(String strTitle){
//        txt = strTitle;
//        textView.append(txt);
//    }

//    private void getPhotos(){
//        Call<List<Photos>> call = jsonPlaceHolderApi.getPhotos();
//
//        call.enqueue(new Callback<List<Photos>>() {
//            @Override
//            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
//                List<Photos> photos = response.body();
//                for (Photos photo : photos){
//                    String str = "";
//                    str += photo.getAlbumId() + "\n";
//                    str += photo.getId() + "\n";
//                    str += photo.getTitle() + "\n";
//                    str += photo.getUrl() + "\n";
//                    str += photo.getThumbnailUrl() + "\n\n";
//
//                    textView.append(str);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Photos>> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void getAlbums(){
//        Call<List<Albums>> call = jsonPlaceHolderApi.getAlbums(mass);
//
//        call.enqueue(new Callback<List<Albums>>() {
//            @Override
//            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
//                List<Albums> albums = response.body();
//                for (Albums album : albums){
//                    String str = "";
//                    str += "userId - " + album.getUserId() + "\n";
//                    str += "id - " + album.getId() + "\n";
//                    str += "title - " + album.getTitle() + "\n\n";
//
//                    textView.append(str);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Albums>> call, Throwable t) {
//            }
//        });
//    }
//
//
//
//    private void getPost(){
//        Call<List<Post>> call = jsonPlaceHolderApi.getPost(mass);
//
//        call.enqueue(new Callback<List<Post>>(){
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                List<Post> posts = response.body();
//                String str = "";
//                for (Post post : posts ){
//                    str += post.getTitle() + "\n";
//                    str += post.getBody() + "\n";
//                }
//                TextAndTitle(str);
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//            }
//        });
//    }

//    private void getComments(){
//        Call<List<Comments>> call = jsonPlaceHolderApi.getComm(mass);
//
//        call.enqueue(new Callback<List<Comments>>() {
//            @Override
//            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
//                List<Comments> comments = response.body();
//                for (Comments comm : comments){
//                    String text = "";
//                    text += "postId - " + comm.getPostId() + "\n";
//                    text += "id - " + comm.getId() + "\n";
//                    text += "name - " + comm.getName() + "\n";
//                    text += "email - " + comm.getEmail() + "\n";
//                    text += "body - " + comm.getBody() + "\n\n";
//
//                    textView.append(text);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Comments>> call, Throwable t) {
//
//            }
//        });
//    }
//

    public int[] createMassiv(){
        int[] m = new int[10];
        for (int i = 0; i < 10; i++){
            m[i] = (int)(Math.random() * 100);
        }
        return m;
    }
}
