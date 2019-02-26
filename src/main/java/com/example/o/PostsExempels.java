package com.example.o;

public class PostsExempels {

    private String Title;
    private String Text;
    private String FirstLater;

    public PostsExempels(){
    }

    public PostsExempels(String title, String text) {
        Title = title;
        Text = text;
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



    public void setTitle(String title) {
        Title = title;
    }

    public void setText(String text) {
        Text = text;
    }
}
