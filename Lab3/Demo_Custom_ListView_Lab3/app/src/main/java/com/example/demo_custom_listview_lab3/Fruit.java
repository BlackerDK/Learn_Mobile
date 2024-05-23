package com.example.demo_custom_listview_lab3;

import android.net.Uri;

public class Fruit {
    private  String name;
    private  String des;
    private Integer img;
    private Uri uri;

    private ImageType imageType;

    public Fruit(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
        imageType = ImageType.RESOURCE;
    }
    public void setImg(Uri img) {
        this.uri = img;
        imageType = ImageType.URI;
    }

    public Uri getUri() {
        return uri;
    }

    public ImageType getImageType() {
        return imageType;
    }
}
