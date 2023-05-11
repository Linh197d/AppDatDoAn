package com.example.apphai.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "monan")
public class MonAn implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int resourceId;
    private String name;
    private int price;
    private String mota;
    private int Soluong;
    private String nguyenlieu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public MonAn(int resourceId, String name, int price, String mota, String nguyenlieu) {
        this.resourceId = resourceId;
        this.name = name;
        this.price = price;
        this.mota = mota;
        this.nguyenlieu = nguyenlieu;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getNguyenlieu() {
        return nguyenlieu;
    }

    public void setNguyenlieu(String nguyenlieu) {
        this.nguyenlieu = nguyenlieu;
    }
}
