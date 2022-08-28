package com.example.quanlybanhang.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.Product;

public class ProductDao {
    public static final String SQL_PRODUCT = "CREATE TABLE Product(maSp " +
            "text primary key, tenSp text,gia text);";
    public static final String TABLE_NAME = "Product";

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ProductDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ProductDao(SQLiteDatabase db, DatabaseHelper dbHelper) {
        this.db = db;
        this.dbHelper = dbHelper;
    }

    public int insertProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put("maSp", product.getMaSp());
        values.put("tenSp", product.getTenSp());
        values.put("gia", product.getGia());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e("TAG", ex.toString());
        }
        return 1;
    }

    public int updateTenSp(Product product) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tenSp", product.getTenSp());
        // updating row
        return db.update(TABLE_NAME, values, "maSp = ?",
                new String[]{product.getMaSp()});
    }

    public int deleteProductByID(String maSp) {
        int result = db.delete(TABLE_NAME, "maSp=?", new String[]{maSp});
        if (result == 0)
            return -1;
        return 1;
    }

    public Product getProduct(String maSp) {

        Product product = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TABLE_NAME, new String[]{"maSp", "tenSp", "gia"}, "maSp=?", new String[]{maSp}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            @SuppressLint("Range") String maSp1 = cursor.getString(cursor.getColumnIndex("maSp"));

            @SuppressLint("Range") String tenSp = cursor.getString(cursor.getColumnIndex("tenSp"));

            @SuppressLint("Range") String gia = cursor.getString(cursor.getColumnIndex("gia"));

            product = new Product(maSp1, tenSp, gia);
        }
        cursor.close();
        return product;
    }
}
