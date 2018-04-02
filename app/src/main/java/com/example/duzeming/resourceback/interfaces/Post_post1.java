package com.example.duzeming.resourceback.interfaces;

import com.example.duzeming.resourceback.user.Post;
import com.example.duzeming.resourceback.user.Post1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 陈金桁 on 2018/3/24.
 */

public interface Post_post1 {
    @GET("post_list")
    Call<List<Post>> getCall();
}
