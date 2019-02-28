package com.example.o;

public class PhotoExempels {

    private String id;
    private String photo;
    private String nameOfPhoto;
    private String BigPhoto;

    public PhotoExempels() {
    }

    public PhotoExempels(String id, String photo, String nameOfPhoto, String bigPhoto) {
        this.id = id;
        this.photo = photo;
        this.nameOfPhoto = nameOfPhoto;
        BigPhoto = bigPhoto;
    }

    public String getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNameOfPhoto() {
        return nameOfPhoto;
    }

    public String getBigPhoto() {
        return BigPhoto;
    }
}
