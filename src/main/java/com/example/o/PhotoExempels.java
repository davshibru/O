package com.example.o;

public class PhotoExempels {

    private String photo;
    private String nameOfPhoto;

    public PhotoExempels() {
    }

    public PhotoExempels(String photo, String nameOfPhoto) {
        this.photo = photo;
        this.nameOfPhoto = nameOfPhoto;
    }

    public String getPhoto() {
        return photo;
    }

    public String getNameOfPhoto() {
        return nameOfPhoto;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNameOfPhoto(String nameOfPhoto) {
        this.nameOfPhoto = nameOfPhoto;
    }
}
