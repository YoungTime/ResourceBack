package com.example.duzeming.resourceback.views;

import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duzeming.resourceback.R;
import com.example.duzeming.resourceback.adapter.BackerAdapter;
import com.example.duzeming.resourceback.base.BaseActivity;
import com.example.duzeming.resourceback.interfaces.Post_post1;
import com.example.duzeming.resourceback.user.Post;
import com.example.duzeming.resourceback.user.Post1;
import com.example.duzeming.resourceback.utils.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BackerActivity extends BaseActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<Post> postList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Retrofit retrofit;
    private Intent intent1;
    private Button service;
    private BackerAdapter backAdapter;
    private RetrofitUtil retrofitUtil = new RetrofitUtil();
    @Override
    public int getContent() {
        return R.layout.activity_backer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backAdapter = new BackerAdapter(this);
        Post post1 = new Post();
        post1.setLevel("35Kg");
        post1.setName("杜泽明");
        post1.setPhone("1596465631");
        post1.setAddress("四川省绵阳市三台县");
        post1.setTitle("金属类");
        Post post2 = new Post();
        post1.setLevel("30Kg");
        post1.setName("宋琪飞");
        post1.setPhone("1596569632");
        post1.setAddress("四川省广安市");
        post1.setTitle("塑料类");
        postList.add(post1);
        postList.add(post2);

//        refresh();
        init();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(BackerActivity.this));
        backAdapter.setList(postList);
        recyclerView.setAdapter(backAdapter);
        backAdapter.notifyDataSetChanged();
    }

    private void init(){
        service = (Button) findViewById(R.id.btn_backer_my);
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BackerActivity.this,MyActivity.class));
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                refresh();
//            }
//        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.discuss:
//                startActivity(new Intent(BackerActivity.this,PostActivity.class));
//                break;
//            case R.id.my:
//                startActivity(new Intent(BackerActivity.this,MyActivity.class));
//                break;
//        }
//        return true;
//    }
    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                retrofit = retrofitUtil.createRetrofitUtil("http://119.29.233.28:9090/");
                Post_post1 post_post1 = retrofit.create(Post_post1.class);
                Call<List<Post>> call = post_post1.getCall();
                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        postList = response.body();
                        recyclerView = (RecyclerView) findViewById(R.id.recycler);
                        recyclerView.setLayoutManager(new LinearLayoutManager(BackerActivity.this));
                        backAdapter.setList(postList);
                        recyclerView.setAdapter(backAdapter);
                        backAdapter.notifyDataSetChanged();
                        Toast.makeText(BackerActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        Toast.makeText(BackerActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        intent1 = getIntent();
        if(intent1.getStringExtra("title1") != null){
            Post post3 = new Post();
            post3.setPhone(intent1.getStringExtra("phone1"));
            post3.setMoney(intent1.getStringExtra("price1"));
            post3.setTitle(intent1.getStringExtra("title1"));
            post3.setContent(intent1.getStringExtra("content1"));
            postList.add(post3);
            backAdapter.notifyDataSetChanged();
        }
    }
}
