package com.example.duzeming.resourceback.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.duzeming.resourceback.R;
import com.example.duzeming.resourceback.utils.SnackBarMethod;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private EditText etSurePassword;
    private EditText etPhoneNum;
    private RadioButton radioNormal;
    private RadioButton radioBacker;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


    }

    private void init(){
        etUsername = (EditText) findViewById(R.id.et_register_name);
        etPassword = (EditText) findViewById(R.id.et_register_password);
        etSurePassword = (EditText) findViewById(R.id.et_register_password_sure);
        etPhoneNum = (EditText) findViewById(R.id.et_register_telephone);
        radioNormal = (RadioButton) findViewById(R.id.radio_register_normal);
        radioBacker = (RadioButton) findViewById(R.id.radio_register_backer);
        btnRegister = (Button) findViewById(R.id.btn_register_register);

    }
    private void login(){
        if (etUsername.getText().toString().isEmpty()){
            SnackBarMethod.setSnackbarColor(etUsername,"用户名不能为空");
        }
        if (etPassword.getText().toString().isEmpty()){
            SnackBarMethod.setSnackbarColor(etPassword,"密码不能为空");
        }
        if (etSurePassword.getText().toString().isEmpty()){
            SnackBarMethod.setSnackbarColor(etSurePassword,"确认密码不能为空");
        }
        if (!etPassword.getText().toString().equals(etSurePassword.getText().toString())){
            SnackBarMethod.setSnackbarColor(etPassword,"密码与确认密码不一致");
        }
        if (etPhoneNum.getText().toString().isEmpty()){
            SnackBarMethod.setSnackbarColor(etPhoneNum,"电话不能为空");
        }
    }

    public void doRegister(){
        login();
    }
}
