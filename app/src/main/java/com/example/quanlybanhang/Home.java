package com.example.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quanlybanhang.dao.BillDao;
import com.example.quanlybanhang.dao.DepartmentDao;
import com.example.quanlybanhang.dao.ProductDao;
import com.example.quanlybanhang.dao.UserDao;

public class Home extends AppCompatActivity {


    UserDao userDao;
    ProductDao productDao;
    DepartmentDao departmentDao;
    BillDao billDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userDao = new UserDao(getApplicationContext());
        productDao = new ProductDao(getApplicationContext());
        departmentDao = new DepartmentDao(getApplicationContext());
        billDao = new BillDao(getApplicationContext());
    }
}