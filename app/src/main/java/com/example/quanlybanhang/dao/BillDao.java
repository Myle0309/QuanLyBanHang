package com.example.quanlybanhang.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.Bill;
import com.example.quanlybanhang.model.Department;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillDao {
    public static final String SQL_BILL = "CREATE TABLE  Bill( maHoaDon " +
            "text primary key, soLuong text,maNv text ,maSp text ,ngayLapHoaDon date,tongTien text);";
    public static final String TABLE_NAME = "Bill";


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public BillDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public BillDao(SQLiteDatabase db, DatabaseHelper dbHelper) {
        this.db = db;
        this.dbHelper = dbHelper;
    }

    public int insertBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put("maHoaDon", bill.getMaHoaDon());
        values.put("soLuong", bill.getSoLuong());
        values.put("maNv", bill.getMaNv());
        values.put("maSp", bill.getMaSp());
        values.put("ngayLapHoaDon", sdf.format(bill.getNgayLapHoaDon()));
        values.put("tongTien", bill.getTongTien());

        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e("TAG", ex.toString());
        }
        return 1;
    }

    public int updateSoLuong(Bill bill) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("soLuong", bill.getSoLuong());
        // updating row
        return db.update(TABLE_NAME, values, "maHoaDon = ?",
                new String[]{bill.getMaHoaDon()});
    }

    public int updateTongTien(Bill bill) {
        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tongTien", bill.getTongTien());
        // updating row
        return db.update(TABLE_NAME, values, "maHoaDon = ?",
                new String[]{bill.getMaHoaDon()});
    }

    public int deleteBillByID(String maHoaDon) {
        int result = db.delete(TABLE_NAME, "maHoaDon=?", new String[]{maHoaDon});
        if (result == 0)
            return -1;
        return 1;
    }

    public Bill getBill(String maNv) {

        Bill bill = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TABLE_NAME, new String[]{"maHoaDon", "soLuong", "maNv", "maSp", "ngayLapHoaDon", "tongTien"}, "maNv=?", new String[]{maNv}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            @SuppressLint("Range") String maPhongBan1 = cursor.getString(cursor.getColumnIndex("maHoaDon"));

            @SuppressLint("Range") String soLuong = cursor.getString(cursor.getColumnIndex("soLuong"));

            @SuppressLint("Range") String maNv1 = cursor.getString(cursor.getColumnIndex("maNv"));
            @SuppressLint("Range") String maSp = cursor.getString(cursor.getColumnIndex("maSp"));
            @SuppressLint("Range") String ngayLapHoaDon = cursor.getString(cursor.getColumnIndex("ngayLapHoaDon"));
            @SuppressLint("Range") String tongTien = cursor.getString(cursor.getColumnIndex("tongTien"));

            try {
                bill = new Bill(maPhongBan1, soLuong, maNv1, maSp, sdf.parse(ngayLapHoaDon), tongTien);
            } catch (ParseException e) {
                e.printStackTrace();
                //TODO big bug
                bill = new Bill(maPhongBan1, soLuong, maNv1, maSp, new Date(), tongTien);
            }
        }
        cursor.close();
        return bill;
    }

}
