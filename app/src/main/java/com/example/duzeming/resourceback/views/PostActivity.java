package com.example.duzeming.resourceback.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anonymous.greendao.EntityManager;
import com.example.anonymous.greendao.GreenDaoUserDao;
import com.example.duzeming.resourceback.R;
import com.example.duzeming.resourceback.base.BaseActivity;
import com.example.duzeming.resourceback.interfaces.Post_post;
import com.example.duzeming.resourceback.user.GreenDaoUser;
import com.example.duzeming.resourceback.user.Post;
import com.example.duzeming.resourceback.utils.RetrofitUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostActivity extends BaseActivity {
    private LoginActivity loginActivity = new LoginActivity();
    public static final int TAKE_PHOTO = 1;
    @Bind(R.id.title)
    EditText title;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.price)
    EditText price;
    private ImageView picture;
    private Post post = new Post();
    private Uri imageUri;
    @Override
    public int getContent() {
        return R.layout.activity_post;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picture = (ImageView) findViewById(R.id.picture);
    }
    @OnClick(R.id.send)
    public void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GreenDaoUserDao userDao = EntityManager.getInstance().getUserDao();
                List<GreenDaoUser> userList = userDao.queryBuilder().where(GreenDaoUserDao.Properties.Id.notEq(1))
                        .limit(1)
                        .build().list();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                RetrofitUtil retrofitUtil = new RetrofitUtil();
                Retrofit retrofit = retrofitUtil.createRetrofitUtil("http://119.29.233.28:9090/");
                Post_post post_post = retrofit.create(Post_post.class);
                Call<Post> call = post_post.getCall(userList.get(0).getAccount(),str,title.getText().toString(),content.getText().toString(),phone.getText().toString(),price.getText().toString(),post.getImage());
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Toast.makeText(PostActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Log.e("onFailure",t.getMessage().toString());
                        Toast.makeText(PostActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
    @OnClick(R.id.phote)
    public void phote(){
        File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
        try {
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            imageUri = FileProvider.getUriForFile(PostActivity.this,"com.example.duzeming.resourceback",outputImage);
        }
        else{
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent,TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PHOTO:
                if(requestCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
