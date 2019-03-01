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
import android.util.Log;
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
    JsonPlaceHolderApi jsonPlaceHolderApi2;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private TextView textView;
    final int[] mass = createMassiv(10, 100);
    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mText = new ArrayList<>();
    private String title ="d";
    private String text = "d";
    ArrayList<PostsExempels> list = new ArrayList<>();
    ArrayList<PostsExempels> weatherList = new ArrayList<>();
    ArrayList<AlbumExsempels> AlbList = new ArrayList<>();
    int[] commentMass = createCommentMassiv(mass);
    final String KEYAPI = "802cfd103b0a97ccd766dd7f86a00d43";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        jsonPlaceHolderApi2 = retrofit2.create(JsonPlaceHolderApi.class);

        getWeather("Bishkek");
        getWeather("Cholpon-Ata");
        getWeather("Naryn");
        getWeather("Osh");

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

    private void getWeather(String city){
        Call<WeatherWeather> call = jsonPlaceHolderApi2.getWeather(city, KEYAPI);

        call.enqueue(new Callback<WeatherWeather>() {
            @Override
            public void onResponse(Call<WeatherWeather> call, Response<WeatherWeather> response) {

                WeatherWeather weather = response.body();
                String getName = weather.getName();
                String getTemp = "" + weather.getMain().temp;
                List<Weather> weatherIcon = weather.getWeather();
                String icon = "";
                for (Weather weath : weatherIcon){
                    icon += weath.getIcon();
                    break;
                }
                weatherList.add(new PostsExempels(getName,getTemp,icon,true));
            }

            @Override
            public void onFailure(Call<WeatherWeather> call, Throwable t) {
                Log.e("Problem", t.getMessage());
            }
        });
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


    public ArrayList<PostsExempels> getPost() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPost(mass);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                int[] massFour = createMassiv(4,10);
                int i = 0;
                int count = 0;
                for (Post post : posts) {
                    int Number = 0;
                    if (massFour[0] == i || massFour[1] == i || massFour[2] == i || massFour[3] == i){
                        for (PostsExempels weather : weatherList){
                            if (count == Number) {
                                list.add(new PostsExempels(weather.getTitle(), weather.getText(), weather.getId(), weather.isWeatherOrPost()));
                                Number = 0;
                                break;
                            }
                            Number++;
                        }
                        count++;
                    }

                    String id = "" + post.getId();
                    list.add(new PostsExempels(post.getTitle(),post.getBody(),id,false));
                    i++;
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


    public int[] createMassiv(int ten, int diopazon){
        int[] m = new int[ten];
        for (int i = 0; i < ten; i++){
            m[i] = (int)(Math.random() * diopazon);
        }
        return m;
    }

    public int[] createCommentMassiv(int[] mass){
        int[] m = new int[mass.length + 10];
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
