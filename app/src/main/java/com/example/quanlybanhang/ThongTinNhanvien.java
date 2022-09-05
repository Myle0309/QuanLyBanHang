package com.example.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlybanhang.dao.UserDao;
import com.example.quanlybanhang.model.User;
import com.google.gson.Gson;

import java.util.Calendar;

public class ThongTinNhanvien extends AppCompatActivity {

    UserDao userDao;
    private SharedPreferences pref;

    View btnThem;
    View Huy;
    EditText edtmaNV, edthoTen, edtphai, edtngaySinh, edtdienThoai, edtDiaChi, edtEmail;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhanvien);
        userDao = new UserDao(getApplicationContext());
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        user = userDao.getUser(new Gson().fromJson(pref.getString(Constant.KEY_USER, ""), User.class).getUserName());
        initView();
        btnThem.setOnClickListener(v -> {
            if (validateForm() > 0) {
                user.setMaNV(edtmaNV.getText().toString());
                user.setHoTen(edthoTen.getText().toString());
                user.setPhai(edtphai.getText().toString());
                user.setNgaySinh(edtngaySinh.getText().toString());
                user.setDienThoai(edtdienThoai.getText().toString());
                user.setEmail(edtEmail.getText().toString());
                user.setDiachi(edtDiaChi.getText().toString());
                userDao.insertUser(user);
                pref.edit().putString(Constant.KEY_USER, new Gson().toJson(user)).commit();
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        Huy.setOnClickListener(v -> {
            startActivity(new Intent(ThongTinNhanvien.this,QuanLyNhanVienActivity.class));
        });
    }

    private void initView() {
        btnThem = findViewById(R.id.btnThem);
        Huy = findViewById(R.id.Huy);
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

    private int validateForm() {
        int check = 1;
        if (edtmaNV.getText().length() == 0
                || edthoTen.getText().length() == 0
                || edtphai.getText().length() == 0
                || edtngaySinh.getText().length() == 0
                || edtdienThoai.getText().length() == 0
                || edtDiaChi.getText().length() == 0
                || edtEmail.getText().length() == 0
        ) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
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
}