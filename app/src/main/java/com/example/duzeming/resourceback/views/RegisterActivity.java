package com.example.duzeming.resourceback.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.duzeming.resourceback.R;
import com.example.duzeming.resourceback.interfaces.PostReuest;
import com.example.duzeming.resourceback.user.User;
import com.example.duzeming.resourceback.utils.RetrofitUtil;
import com.example.duzeming.resourceback.utils.SnackBarMethod;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

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
        else{
            RetrofitUtil retro = new RetrofitUtil();
            Retrofit retrofit = retro.createRetrofitUtil("");
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

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                             Log.d("TAG", "请求失败");
                        }

                        @Override
                        public void onComplete() {
                            Log.d("TAG", "请求成功");
                        }
                    });
        }
    }

    public void doRegister(){
        login();

    }
}
