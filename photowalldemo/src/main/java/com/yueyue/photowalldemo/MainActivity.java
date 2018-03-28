package com.yueyue.photowalldemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 照片墙主活动，使用GridView展示照片墙。
 */
public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    /**
     * 用于展示照片墙的GridView
     */
    private GridView mPhotoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter adapter;

    private Images mImages = new Images();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler(Looper.getMainLooper());
        initData();
        initAdapter();
    }

    private void initData() {
        String imageUrl = "http://gank.io/api/data/" + URLEncoder.encode("福利") + "/50/1";
        HttpUtil.sendOkHttpRequest(imageUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.postDelayed(() -> {
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    mImages.results.clear();
                    adapter.notifyDataSetChanged();
                }, 0);
            }

            @Override
            public void onResponse(Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String content = response.body().string();
                    mImages.results.clear();
                    Images images = new Gson().fromJson(content, Images.class);
                    mImages.results.addAll(images.results);
                    mHandler.postDelayed(() -> {
                        Toast.makeText(MainActivity.this, "数据请求成功", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }, 0);

                } else {
                    onFailure(call, new IOException("解析错误"));
                }
            }
        });
    }

    private void initAdapter() {
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        adapter = new PhotoWallAdapter(this, 0, mImages.results, mPhotoWall);
        mPhotoWall.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }

}
