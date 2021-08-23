package com.example.quanlytaikhoanthanhtoansinhvien.QuanLyMonHocDaDangKy;

public class Subject {
    String id;
    String name;
    String money;
    String time;
    String location;
    String quantityStudent;

    public Subject(String id, String name, String money, String time, String location, String quantityStudent) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.time = time;
        this.location = location;
        this.quantityStudent = quantityStudent;
    }

    public String getId() {return id;}

    public String getName() {
        return name;
    }

    public String getMoney() { return money; }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getQuantityStudent() { return quantityStudent; }
}