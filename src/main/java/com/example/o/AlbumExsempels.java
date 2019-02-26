package com.example.o;

public class AlbumExsempels {

    private String nameOfAlb;

    public AlbumExsempels(){
    }

    public AlbumExsempels(String nameOfAlb) {
        this.nameOfAlb = nameOfAlb;
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
