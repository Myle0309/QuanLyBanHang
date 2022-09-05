package com.example.quanlybanhang;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlybanhang.dao.UserDao;
import com.example.quanlybanhang.model.User;
import com.google.gson.Gson;

import java.util.Calendar;

public class ChiTietNguoiDungActivity extends AppCompatActivity {

    UserDao userDao;
    EditText edtmaNV, edthoTen, edtphai, edtngaySinh, edtdienThoai, edtDiaChi, edtEmail;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nguoi_dung);
        setTitle("Chi Tiết Người Dùng");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initView();
        userDao = new UserDao(ChiTietNguoiDungActivity.this);
        user = new Gson().fromJson(getIntent().getStringExtra("USER"), User.class);
        fillData();
        findViewById(R.id.btnUpdate).setOnClickListener(v -> {
            user.setMaNV(edtmaNV.getText().toString());
            user.setHoTen(edthoTen.getText().toString());
            user.setPhai(edtphai.getText().toString());
            user.setNgaySinh(edtngaySinh.getText().toString());
            user.setDienThoai(edtdienThoai.getText().toString());
            user.setEmail(edtEmail.getText().toString());
            user.setDiachi(edtDiaChi.getText().toString());
            userDao.update(user.getMaNV(), user);
            Toast.makeText(getApplicationContext(), "Cập nhập thành công", Toast.LENGTH_SHORT).show();
            finish();
        });
        findViewById(R.id.Huy).setOnClickListener(v -> {
            finish();
        });
    }

    private void fillData() {
        ((EditText) findViewById(R.id.edtmaNV)).setText(user.getMaNV());
        ((EditText) findViewById(R.id.edthoTen)).setText(user.getHoTen());
        ((EditText) findViewById(R.id.edtphai)).setText(user.getPhai());
        ((EditText) findViewById(R.id.edtngaySinh)).setText(user.getNgaySinh());
        ((EditText) findViewById(R.id.edtdienThoai)).setText(user.getDienThoai());
        ((EditText) findViewById(R.id.edtDiaChi)).setText(user.getDiachi());
        ((EditText) findViewById(R.id.edtEmail)).setText(user.getEmail());
    }

    private void initView() {
        edtmaNV = findViewById(R.id.edtmaNV);
        edthoTen = findViewById(R.id.edthoTen);
        edtphai = findViewById(R.id.edtphai);
        edtngaySinh = findViewById(R.id.edtngaySinh);
        edtdienThoai = findViewById(R.id.edtdienThoai);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtEmail = findViewById(R.id.edtEmail);
        edtngaySinh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePicker();
                }
            }
        });
    }
    public void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // thiet lap thong tin cho date picker
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Integer n = year;
                Integer t = month + 1;
                Integer d = dayOfMonth;
                edtngaySinh.setText(d.toString() + "-" + t.toString() + "-" + n.toString());
            }
        }, year, month, day);
        datePickerDialog.show();
    }


    public void Huy(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionnguoidung, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.DeleteNV:
                userDao.deleteUserByID(user.getUserName());
                SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}