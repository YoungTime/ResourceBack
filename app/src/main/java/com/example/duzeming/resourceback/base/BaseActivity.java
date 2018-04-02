package com.example.duzeming.resourceback.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 陈金桁 on 2018/3/24.
 */


public abstract class BaseActivity extends AppCompatActivity {
    public abstract int getContent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContent());
        ButterKnife.bind(this);
    }
}
