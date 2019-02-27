package com.example.o;

public class Comments {
    private int postId;
    private String name;
    private String email;
    private String body;

    public Comments() {
    }

    public Comments(int postId, String name, String email, String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}
