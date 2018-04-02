package com.example.duzeming.resourceback.interfaces;

import com.example.duzeming.resourceback.user.Post;
import com.example.duzeming.resourceback.user.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 陈金桁 on 2018/3/24.
 */

public interface Post_post {
    @POST("post")
    @FormUrlEncoded
    Call<Post> getCall(@Field("user") String account, @Field("createTime") String createTime, @Field("title") String title, @Field("content") String content, @Field("phone") String phone, @Field("price") String price, @Field("image") String image);
}

