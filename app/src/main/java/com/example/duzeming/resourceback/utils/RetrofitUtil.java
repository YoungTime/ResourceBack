package com.example.duzeming.resourceback.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 陈金桁 on 2018/3/24.
 */

public class RetrofitUtil {
    private Retrofit retrofit;
    public Retrofit createRetrofitUtil(String url){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
