package com.example.quanlybanhang.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.Department;
import com.example.quanlybanhang.model.Role;
import com.example.quanlybanhang.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE  User(userName text, password text, maNV " +
            "text primary key, hoten text,phai text ,ngaySinh text ,dienThoai text,phong text, role text);";
    public static final String TABLE_NAME = "User";

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public UserDao(SQLiteDatabase db, DatabaseHelper dbHelper) {
        this.db = db;
        this.dbHelper = dbHelper;
    }


    public int insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("userName", user.getUserName());
        values.put("password", user.getPassword());
        values.put("maNV", user.getMaNV());
        values.put("hoten", user.getHoTen());
        values.put("phai", user.getPhai());
        values.put("ngaySinh", user.getNgaySinh());
        values.put("dienThoai", user.getDienThoai());
        values.put("phong", new Gson().toJson(user.getPhong()));
        values.put("role", user.getRole().name());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e("TAG", ex.toString());
        }
        return 1;
    }

    public List<User> getAllUser() {
        List<User> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User ee = new User();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setMaNV(c.getString(2));
            ee.setHoTen(c.getString(3));
            ee.setPhai(c.getString(4));
            ee.setNgaySinh(c.getString(5));
            ee.setDienThoai(c.getString(6));
            ee.setPhong(new Gson().fromJson(c.getString(7), Department.class));
            ee.setRole(c.getString(8).equals(Role.CLIENT.name()) ? Role.CLIENT : Role.STAFF);
            dsNguoiDung.add(ee);
            Log.d("//======", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    public int changePassword(String username, User user) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("password", user.getPassword());


        // updating row
        return db.update(TABLE_NAME, values, "userName = ?",
                new String[]{username});
    }

    public int update(String username, User user) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maNV", user.getPassword());
        values.put("hoTen", user.getPassword());
        values.put("phai", user.getPassword());
        values.put("ngaySinh", user.getPassword());
        values.put("dienThoai", user.getPassword());
        Department department = user.getPhong();
        values.put("phong", new Gson().toJson(department));

        // updating row
        return db.update(TABLE_NAME, values, "userName = ?",
                new String[]{username});
    }

    public int deleteUserByID(String username) {
        int result = db.delete(TABLE_NAME, "userName=?", new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }


    public User getUser(String username) {

        User user = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TABLE_NAME, new String[]{"userName", "password", "maNV", "hoten ", "phai", "ngaySinh", "dienThoai", "phong", "role"}, "userName=?", new String[]{username}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            @SuppressLint("Range") String userName = cursor.getString(cursor.getColumnIndex("userName"));

            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));

            @SuppressLint("Range") String maNV = cursor.getString(cursor.getColumnIndex("maNV"));
            @SuppressLint("Range") String hoTen = cursor.getString(cursor.getColumnIndex("hoten"));

            @SuppressLint("Range") String phai = cursor.getString(cursor.getColumnIndex("phai"));
            @SuppressLint("Range") String ngaySinh = cursor.getString(cursor.getColumnIndex("ngaySinh"));
            @SuppressLint("Range") String dienThoai = cursor.getString(cursor.getColumnIndex("dienThoai"));
            @SuppressLint("Range") Department department = new Gson().fromJson(cursor.getString(cursor.getColumnIndex("phong")), Department.class);
            @SuppressLint("Range") Role role = cursor.getString(cursor.getColumnIndex("role")).equals(Role.CLIENT) ? Role.CLIENT : Role.STAFF;

            // khoi tao user voi cac gia tri lay duoc
            user = new User(userName, password, maNV, hoTen, phai, ngaySinh, dienThoai, department, role);
        }
        cursor.close();
        return user;
    }

}
