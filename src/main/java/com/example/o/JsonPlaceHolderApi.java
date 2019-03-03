package com.example.o;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("data/2.5/weather/")
    Call<WeatherWeather> getWeather(@Query("q") String q,
                                    @Query("appid") String APPID);

    @GET("posts/")
    Call<List<Post>> getPost(@Query("id") int[] id);

    @GET("comments")
    Call<List<Comments>> getComm(@Query("postId") int[] postId);

    @GET("albums")
    Call<List<Albums>> getAlbums(@Query("id") int[] id);

    @GET("photos")
    Call<List<Photos>> getPhotos(@Query("albumId") int[] i);
}
