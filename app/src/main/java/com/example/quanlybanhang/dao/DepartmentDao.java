package com.example.quanlybanhang.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.Department;
import com.example.quanlybanhang.model.Product;
import com.example.quanlybanhang.model.User;
import com.google.gson.Gson;

public class DepartmentDao {
    public static final String SQL_DEPARTMENT = "CREATE TABLE  Department( maPhongBan " +
            "text primary key, maNv text,tenPhong text);";
    public static final String TABLE_NAME = "Department";


    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DepartmentDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public DepartmentDao(SQLiteDatabase db, DatabaseHelper dbHelper) {
        this.db = db;
        this.dbHelper = dbHelper;
    }

    public int insertDepartment(Department department) {
        ContentValues values = new ContentValues();
        values.put("maPhongBan", department.getMaPhongBan());
        values.put("maNv", department.getMaNv());
        values.put("tenPhong", department.getTenPhong());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e("TAG", ex.toString());
        }
        return 1;
    }

    public int updateTenPhong(Department department) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tenPhong", department.getTenPhong());

        // updating row
        return db.update(TABLE_NAME, values, "maPhongBan = ?",
                new String[]{department.getMaPhongBan()});
    }

    public int deleteDepartmentByID(String maPhongBan) {
        int result = db.delete(TABLE_NAME, "maPhongBan=?", new String[]{maPhongBan});
        if (result == 0)
            return -1;
        return 1;
    }

    public Department getDepartment(String maNv) {

        Department department = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TABLE_NAME, new String[]{"maPhongBan", "maNv", "tenPhong"}, "maNv=?", new String[]{maNv}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            @SuppressLint("Range") String maPhongBan = cursor.getString(cursor.getColumnIndex("maPhongBan"));

            @SuppressLint("Range") String maNv1 = cursor.getString(cursor.getColumnIndex("maNv"));

            @SuppressLint("Range") String tenPhong = cursor.getString(cursor.getColumnIndex("tenPhong"));

            department = new Department(maPhongBan, maNv1, tenPhong);
        }
        cursor.close();
        return department;
    }
}
