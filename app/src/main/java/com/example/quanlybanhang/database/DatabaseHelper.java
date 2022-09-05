package com.example.quanlybanhang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlybanhang.dao.BillDao;
import com.example.quanlybanhang.dao.ProductDao;
import com.example.quanlybanhang.dao.UserDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbProductManager";
    public static final int VESION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDao.SQL_NGUOI_DUNG);
        db.execSQL(ProductDao.SQL_PRODUCT);
        db.execSQL(BillDao.SQL_BILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + UserDao.TABLE_NAME);
        db.execSQL("Drop table if exists " + ProductDao.TABLE_NAME);
        db.execSQL("Drop table if exists " + BillDao.TABLE_NAME);
        onCreate(db);
    }
}
