package com.example.quanlybanhang.model;

public class Department {
    String maPhongBan;
    String maNv;
    String tenPhong;

    public Department(String maPhongBan, String maNv, String tenPhong) {
        this.maPhongBan = maPhongBan;
        this.maNv = maNv;
        this.tenPhong = tenPhong;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }
}
