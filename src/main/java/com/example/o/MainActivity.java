package com.example.o;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    final int[] mass = createMassiv(10, 100);
    JsonPlaceHolderApi jsonPlaceHolderApi, jsonPlaceHolderApi2;
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> text = new ArrayList<>();
    ArrayList<String> Id = new ArrayList<>();
    ArrayList<PostsExempels> weatherList = new ArrayList<>();
    ArrayList<AlbumExsempels> AlbList = new ArrayList<>();
    final String KEYAPI = "802cfd103b0a97ccd766dd7f86a00d43";
    String str;


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

        getPost();

        tabLayout = (TabLayout) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new PostFragment(),"");
        adapter.AddFragment(new AlbumsFragment(), "");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_feedback_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_image_black_24dp);

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

    public void getPhotos(int[] i){
        Call<List<Photos>> call = jsonPlaceHolderApi.getPhotos(i);

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

    public void getPost() {

        Call<List<Post>> call = jsonPlaceHolderApi.getPost(mass);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                int[] massFour = createMassiv(4,10);
                int i = 0;
                int count = 0;
                for (Post post : posts) {
//                    int Number = 0;
//                    if (massFour[0] == i || massFour[1] == i || massFour[2] == i || massFour[3] == i){
//                        for (PostsExempels weather : weatherList){
//                            if (count == Number) {
//
//                                Number = 0;
//                                break;
//                            }
//                            Number++;
//                        }
//                        count++;
//                    }

                    String id = "" + post.getId();
                    title.add(post.getTitle());
                    text.add( post.getBody());
                    Id.add(id);
                    str = post.getTitle();
//                    i++;
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }


    public void getComments(int[] commMass){
        Call<List<Comments>> call = jsonPlaceHolderApi.getComm(commMass);

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

}
