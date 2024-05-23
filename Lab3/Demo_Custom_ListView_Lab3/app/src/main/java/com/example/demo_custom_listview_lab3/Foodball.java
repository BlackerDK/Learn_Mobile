package com.example.demo_custom_listview_lab3;

public class Foodball {
    private  String name;
    private  String des;

    private Object avatart;

    private Object flag;

    public Foodball(String name, String des, Object avatart, Object flag) {
        this.name = name;
        this.des = des;
        this.avatart = avatart;
        this.flag = flag;
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

    public Object getAvatart() {
        return avatart;
    }

    public void setAvatart(Object avatart) {
        this.avatart = avatart;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }
}
