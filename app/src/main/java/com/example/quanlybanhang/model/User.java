package com.example.quanlybanhang.model;


public class User {
    private String userName = "";
    private String password = "";
    private String maNV = "";
    private String hoTen = "";
    private String phai = "";
    private String ngaySinh = "";
    private String dienThoai = "";
    private Department phong = null;
    private Role role = Role.STAFF;

    public User(){}

    public User(String userName, String password, String hoTen, String dienThoai) {
        this.userName = userName;
        this.password = password;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
    }

    public User(
            String userName,
            String password,
            String maNV, String hoTen, String phai, String ngaySinh, String dienThoai, Department phong, Role role) {
        this.userName = userName;
        this.password = password;
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.phai = phai;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.phong = phong;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public Department getPhong() {
        return phong;
    }

    public void setPhong(Department phong) {
        this.phong = phong;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
