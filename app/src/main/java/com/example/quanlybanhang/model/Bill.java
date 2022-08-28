package com.example.quanlybanhang.model;

import java.util.Date;

public class Bill {
    String maHoaDon;
    String soLuong;
    String maNv;
    String maSp;
    Date ngayLapHoaDon;
    String tongTien;

    public Bill(String maHoaDon, String soLuong, String maNv, String maSp, Date ngayLapHoaDon, String tongTien) {
        this.maHoaDon = maHoaDon;
        this.soLuong = soLuong;
        this.maNv = maNv;
        this.maSp = maSp;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
