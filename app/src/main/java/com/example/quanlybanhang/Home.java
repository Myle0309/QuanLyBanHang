package com.example.quanlybanhang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        updateNhanVien();

        findViewById(R.id.Thoat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setMessage("Bạn có muốn thoát chương trình ?");
                builder.setPositiveButton("Ok", (dialogInterface, i) -> {
                    finish();
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.create();
                builder.show();
            }
        });
    }

    private void updateNhanVien() {
        findViewById(R.id.btnUpdateNhanVien).setOnClickListener(v -> {
            Intent c = new Intent(Home.this, ThongTinNhanvien.class);
            startActivity(c);
        });
    }
}