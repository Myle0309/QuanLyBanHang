package com.example.quanlybanhang.model;

public class Product {
    String maSp;
    String tenSp;
    String gia;

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public Product(String maSp, String tenSp, String gia) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.gia = gia;
    }
}
