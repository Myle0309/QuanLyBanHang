package com.example.quanlybanhang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quanlybanhang.dao.BillDao;
import com.example.quanlybanhang.dao.ProductDao;
import com.example.quanlybanhang.dao.UserDao;

public class Home extends AppCompatActivity {


    UserDao userDao;
    ProductDao productDao;
    BillDao billDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userDao = new UserDao(getApplicationContext());
        productDao = new ProductDao(getApplicationContext());
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
        findViewById(R.id.btnQuanLyKH).setOnClickListener(v -> {
            Intent c = new Intent(Home.this, QuanLyKhachHang.class);
            startActivity(c);
        });

        findViewById(R.id.btnQuanLySp).setOnClickListener(v -> {
            Intent c = new Intent(Home.this, QuanLySanPham.class);
            startActivity(c);
        });
        findViewById(R.id.baocaothongke).setOnClickListener(v -> {
            Intent c = new Intent(Home.this, BaoCaoThongKe.class);
            startActivity(c);
        });
    }

    private void updateNhanVien() {
        findViewById(R.id.btnQuanLyNhanVien).setOnClickListener(v -> {
            Intent c = new Intent(Home.this, QuanLyNhanVienActivity.class);
            startActivity(c);
        });
    }
}