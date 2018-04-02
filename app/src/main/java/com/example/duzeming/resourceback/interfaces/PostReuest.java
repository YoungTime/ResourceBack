package com.example.duzeming.resourceback.interfaces;

import com.example.duzeming.resourceback.user.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 陈金桁 on 2018/3/24.
 */

public interface PostReuest {
    @POST("sign_in/")
    @FormUrlEncoded
    Observable<User> getCall(@Field("account") String name, @Field("password") String word, @Field("userId") String userId, @Field("userList")List<User> userList);
}
