package com.example.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlybanhang.dao.UserDao;
import com.example.quanlybanhang.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVienActivity extends AppCompatActivity {
    public static List<User> dsNguoiDung = new ArrayList<>();

    ListView lvnguoidung;
    NguoiDungAdapter adapter = null;

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        userDao = new UserDao(getApplicationContext());

        lvnguoidung = findViewById(R.id.customlvnguoidung);
        userDao = new UserDao(QuanLyNhanVienActivity.this);
        dsNguoiDung = userDao.getAllUser();
        try {
            dsNguoiDung.remove(0);
            adapter = new NguoiDungAdapter(dsNguoiDung, this);
            if (dsNguoiDung.isEmpty()) {
                lvnguoidung.setVisibility(View.GONE);
                findViewById(R.id.tvKoCoNV).setVisibility(View.VISIBLE);
            } else {
                lvnguoidung.setVisibility(View.VISIBLE);
                findViewById(R.id.tvKoCoNV).setVisibility(View.GONE);
            }
            lvnguoidung.setAdapter(adapter);
            adapter.callBack = new NguoiDungAdapter.CallBack() {
                @Override
                public void onItemSelect(User user, int position) {
                    Intent intent = new Intent(QuanLyNhanVienActivity.this, ChiTietNguoiDungActivity.class);
                    intent.putExtra("USER", new Gson().toJson(user));
                    startActivity(intent);
                }

                @Override
                public void onItemDelete(User user, int position) {
                    userDao.deleteUserByID(user.getMaNV());
                    dsNguoiDung = userDao.getAllUser();
                    adapter.notifyDataSetChanged();
                }
            };
        } catch (Exception e) {
            lvnguoidung.setVisibility(View.GONE);
            findViewById(R.id.tvKoCoNV).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.themnv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNV:
                Intent b = new Intent(QuanLyNhanVienActivity.this, ThongTinNhanvien.class);
                startActivity(b);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}