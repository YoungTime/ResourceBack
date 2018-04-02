package com.example.duzeming.resourceback.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anonymous.greendao.EntityManager;
import com.example.anonymous.greendao.GreenDaoUserDao;
import com.example.duzeming.resourceback.R;
import com.example.duzeming.resourceback.interfaces.PostReuest;
import com.example.duzeming.resourceback.user.GreenDaoUser;
import com.example.duzeming.resourceback.user.User;
import com.example.duzeming.resourceback.utils.RetrofitUtil;
import com.example.duzeming.resourceback.utils.SnackBarMethod;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

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
            RetrofitUtil retrofitUtil = new RetrofitUtil();
            Retrofit retrofit = retrofitUtil.createRetrofitUtil("");
            PostReuest postReuest = retrofit.create(PostReuest.class);
            Observable<User> observable = postReuest.getCall(etUsername.getText().toString(),etPassword.getText().toString(),null,null);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull User user) {
                            if(user.getFlag() == 1){
                                GreenDaoUserDao userDao = EntityManager.getInstance().getUserDao();
                                GreenDaoUser greenDaoUser = new GreenDaoUser(0l,etUsername.getText().toString());
                                userDao.insert(greenDaoUser);
                                Intent intent = new Intent(LoginActivity.this,BackerActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }


}
