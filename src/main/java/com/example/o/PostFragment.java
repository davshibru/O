package com.example.o;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {
    private RecyclerView myrecyclerView;
    private static ArrayList<PostsExempels> lstPost = new ArrayList<>();
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    ArrayList<PostsExempels> weatherList;
    private int[] mass = createMassiv(10,100);
    final String KEYAPI = "802cfd103b0a97ccd766dd7f86a00d43";

    public PostFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstPost = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        myrecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);

        ((MainActivity)getActivity()).getComments(mass);
        getPost();
        getWeather("Bishkek");
        getWeather("Cholpon-Ata");
        getWeather("Naryn");
        getWeather("Osh");

        return view;

    }

    public void getPost() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPost(mass);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                int[] massFour = createMassiv(4,10);
                int count = 0;
                int i = 0;
                for (Post post : posts) {
                    int Number = 0;
                    if (massFour[0] == i || massFour[1] == i || massFour[2] == i || massFour[3] == i || count == 10 && i < 4){
                        for (PostsExempels weather : weatherList){
                            if (count == Number) {
                                lstPost.add(new PostsExempels(weather.getTitle(), weather.getText(), weather.getId(), weather.isWeatherOrPost()));
                                break;
                            }
                            Number++;
                        }
                        count++;
                    }
                    String id = "" + post.getId();
                    String title = post.getTitle();
                    String text  = post.getBody();
                    lstPost.add(new PostsExempels(title,text,id,false));

                    RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(), lstPost);
                    myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    myrecyclerView.setAdapter(recycleViewAdapter);
                    i++;
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
            }
        });
    }

    private void getWeather(String city){

        weatherList = new ArrayList<>();

        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi2 = retrofit2.create(JsonPlaceHolderApi.class);

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
                    Log.e("weather", weather.getName());

            }


            @Override
            public void onFailure(Call<WeatherWeather> call, Throwable t) {
                Log.e("Problem", t.getMessage());
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