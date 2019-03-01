package com.example.o;

public class PostsExempels {

    public static void PostReady(){

    String dav = "dav";
    String tex = "tex";
}

    private String Id;
    private String Title;
    private String Text;
    private String FirstLater;
    private boolean weatherOrPost;

    public PostsExempels(){
    }

    public PostsExempels(String title, String text, String id, boolean weatherOrPost) {
        Id = id;
        Title = title;
        Text = text;
        this.weatherOrPost = weatherOrPost;
    }

    public boolean isWeatherOrPost() {
        return weatherOrPost;
    }

    public String getTitle() {
        return Title;
    }

    public String getText() {
        return Text;
    }

    public String getFirstLater() {
        String m = "" + Title.charAt(0);
        return m;
    }

    public String getId() {
        return Id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setText(String text) {
        Text = text;
    }
}
