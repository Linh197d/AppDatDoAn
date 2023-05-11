package com.example.apphai.model;

public class LichSu {
    private String madonhang;
    private String hoten;
    private String sodthoai;
    private String diachi;
    private String ngay;
    private String Tongtien;

    public LichSu(String madonhang, String hoten, String sodthoai, String diachi, String ngay, String tongtien) {
        this.madonhang = madonhang;
        this.hoten = hoten;
        this.sodthoai = sodthoai;
        this.diachi = diachi;
        this.ngay = ngay;
        Tongtien = tongtien;
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSodthoai() {
        return sodthoai;
    }

    public void setSodthoai(String sodthoai) {
        this.sodthoai = sodthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTongtien() {
        return Tongtien;
    }

    public void setTongtien(String tongtien) {
        Tongtien = tongtien;
    }
}
