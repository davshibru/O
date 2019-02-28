package com.example.o;

public class AlbumExsempels {

    private String nameOfAlb;
    private String idOfAlb;

    public AlbumExsempels(){
    }

    public AlbumExsempels(String nameOfAlb, String idOfAlb) {
        this.nameOfAlb = nameOfAlb;
        this.idOfAlb = idOfAlb;
    }

    public String getIdOfAlb() {
        return idOfAlb;
    }

    public String getNameOfAlb() {
        return nameOfAlb;
    }

    public String getFiresLater() {
        String l = "" + nameOfAlb.charAt(0);
        return  l;
    }

    public void setNameOfAlb(String nameOfAlb) {
        this.nameOfAlb = nameOfAlb;
    }
}
