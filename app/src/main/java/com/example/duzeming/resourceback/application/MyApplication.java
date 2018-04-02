package com.example.duzeming.resourceback.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by 陈金桁 on 2018/3/30.
 */

public class MyApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mContext = getApplicationContext();
    }
    public static Context getmContext(){
        return mContext;
    }
}
