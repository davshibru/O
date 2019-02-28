package com.example.o;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts/")
    Call<List<Post>> getPost(@Query("id") int[] id);

    @GET("comments")
    Call<List<Comments>> getComm(@Query("postId") int[] postId);

    @GET("albums")
    Call<List<Albums>> getAlbums(@Query("id") int[] id);

    @GET("photos")
    Call<List<Photos>> getPhotos(@Query("albumId") int[] i);
}
