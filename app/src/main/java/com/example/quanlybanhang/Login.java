package com.example.quanlybanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlybanhang.dao.UserDao;
import com.example.quanlybanhang.database.DatabaseHelper;
import com.example.quanlybanhang.model.User;
import com.google.gson.Gson;

public class Login extends AppCompatActivity implements BottomSheetRegister.ICallbackRegister {


    private EditText edtUsername;
    private EditText edtPassword;
    private CheckBox cbRemember;
    private TextView tvRegister;
    private Button btnLogin;
    String strUser, strPass;
    UserDao userDao;
    private DatabaseHelper dbHelper;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);
        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        userDao = new UserDao(getApplicationContext());
        tvRegister.setOnClickListener(v -> {
            BottomSheetRegister bottomSheetRegister = new BottomSheetRegister();
            bottomSheetRegister.iCallbackRegister = this;
            bottomSheetRegister.show(getSupportFragmentManager(), BottomSheetRegister.class.getName());
        });
        checkLogin();
        SharedPreferences prefUserFile = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        cbRemember.setChecked(prefUserFile.getBoolean("REMEMBER", false));
        User nguoiDung = new Gson().fromJson(pref.getString(Constant.KEY_USER, new Gson().toJson(new User())), User.class);
        if (!nguoiDung.getUserName().isEmpty() && !nguoiDung.getPassword().isEmpty() && cbRemember.isChecked()) {
            edtUsername.setText(nguoiDung.getUserName());
            edtPassword.setText(nguoiDung.getPassword());
        }
        cbRemember.setOnCheckedChangeListener((buttonView, isChecked) ->
                rememberUser(nguoiDung.getUserName(), nguoiDung.getPassword(), isChecked));
    }

    public void checkLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (userName.isEmpty() || password.isEmpty()) {
                    if (userName.isEmpty())
                        edtUsername.setError(getString(R.string.notify_empty_user));

                    if (password.isEmpty())
                        edtPassword.setError(getString(R.string.notify_empty_pass));
                } else {
                    User user = userDao.getUser(userName);
                    if (user != null && user.getUserName() != null) {
                        if (password.matches(user.getPassword())) {
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                            String userInString = new Gson().toJson(user);
                            pref.edit().putString(Constant.KEY_USER, userInString).commit();
                            Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu chưa chính xác", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Bạn chưa có tài khoản", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if (!status) {
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }


    @Override
    public void onSuccess(User nguoiDung) {
        userDao.insertUser(nguoiDung);
        String userInString = new Gson().toJson(nguoiDung);
        pref.edit().putString(Constant.KEY_USER, userInString).commit();
        edtUsername.setText(nguoiDung.getUserName());
        edtPassword.setText(nguoiDung.getPassword());
        Toast.makeText(Login.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(Login.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
    }
}