package com.example.duzeming.resourceback.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.duzeming.resourceback.R;
import com.example.duzeming.resourceback.utils.SnackBarMethod;

import java.lang.reflect.Method;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnLogin= (Button) findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        etUsername = (EditText) findViewById(R.id.et_login_username);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        btnLogin = (Button) findViewById(R.id.btn_login_login);
        btnRegister = (Button) findViewById(R.id.btn_login_register);
    }
    private void login(){
        if (etUsername.getText().toString().isEmpty()){
            SnackBarMethod.setSnackbarColor(btnLogin,"用户名不能为空");
        }else if (etPassword.getText().toString().isEmpty()){
            SnackBarMethod.setSnackbarColor(btnLogin,"密码不能为空");
        }else {
            Intent intent = new Intent(LoginActivity.this,BackerActivity.class);
            startActivity(intent);
        }
    }


}
