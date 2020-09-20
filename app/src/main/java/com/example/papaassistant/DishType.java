package com.example.papaassistant;

public class DishType {
    private int imgID;
    private String name;

    public DishType(int imgID, String name) {
        this.imgID = imgID;
        this.name = name;
    }

    public int getImgID() {
        return imgID;
    }

    public String getName() {
        return name;
    }
}
