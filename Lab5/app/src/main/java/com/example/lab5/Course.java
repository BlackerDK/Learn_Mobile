package com.example.lab5;

public class Course {
    private Object img;
    private String title;
    private String des;
    private Object icon;

    public Course(Object img, String title, String des, Object icon) {
        this.img = img;
        this.title = title;
        this.des = des;
        this.icon = icon;
    }

    public Course() {
    }

    public Object getImg() {
        return img;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }
}
