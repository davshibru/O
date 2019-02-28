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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//    private static String lstText;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textView;
    final int[] mass = createMassiv();
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();
    private String title ="d";
    private String text = "d";
    ArrayList<PostsExempels> list = new ArrayList<>();
    ArrayList<AlbumExsempels> AlbList = new ArrayList<>();
    int[] commentMass = createCommentMassiv(mass);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getComments();
        getPhotos();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment selectedFragment = null;
                        switch (menuItem.getItemId()){
                            case R.id.PostItem:
                                selectedFragment = PostFragment.newInstance();
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
        transaction.replace(R.id.main_container, PostFragment.newInstance());
        transaction.commit();

    }


    private void getPhotos(){
        Call<List<Photos>> call = jsonPlaceHolderApi.getPhotos(mass);

        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                List<Photos> photos = response.body();
                String str = "";
                for (Photos photo : photos){
                    str += photo.getAlbumId() + "\n";
                    str += photo.getThumbnailUrl() + "\n";
                    str += photo.getTitle() + "\n";
                    str += photo.getUrl() + "\n";


                }
                try {
                    FileOutputStream file = openFileOutput("photos.txt", MODE_PRIVATE);
                    file.write(str.getBytes());
                    file.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {

            }
        });
    }

    public ArrayList<AlbumExsempels> getAlbums(){

        Call<List<Albums>> call = jsonPlaceHolderApi.getAlbums(mass);

        call.enqueue(new Callback<List<Albums>>() {
            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {
                List<Albums> albums = response.body();
                for (Albums album : albums){
                    AlbList.add(new AlbumExsempels(album.getTitle(),album.getId() + ""));
                }
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
            }
        });
        return AlbList;
    }
//
//
//


    public ArrayList<PostsExempels> getPost() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPost(mass);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for (Post post : posts) {
                    list.add(new PostsExempels(post.getTitle(),post.getBody(),post.getId()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
        return list;
    }


    private void getComments(){
            Call<List<Comments>> call = jsonPlaceHolderApi.getComm(commentMass);

            call.enqueue(new Callback<List<Comments>>() {
                @Override
                public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                    List<Comments> comments = response.body();
                    String str = "";
                    for (Comments comm : comments){
                        str += comm.getPostId() + "\n";
                        String im = "" + comm.getName().charAt(0);
                        str += comm.getName() + "\n";
                        str += comm.getBody() + "\n" + "]";
                        str += im.toUpperCase() + "\n";
                        str += comm.getEmail() + "\n";

                    }
                    try {
                        FileOutputStream file = openFileOutput("comment.txt", MODE_PRIVATE);
                        file.write(str.getBytes());
                        file.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
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

    public int[] createCommentMassiv(int[] mass){
        int[] m = new int[20];
        for (int i = 0; i < mass.length; i++){
            m[i] = mass[i];
        }
        m[10] = 3;
        m[11] = 4;
        m[12] = 25;
        m[13] = 31;
        m[14] = 44;
        m[15] = 48;
        m[16] = 51;
        m[17] = 60;
        m[18] = 74;
        m[19] = 80;

        return m;
    }
}
