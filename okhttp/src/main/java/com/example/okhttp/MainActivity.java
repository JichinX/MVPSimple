package com.example.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建OkHttp实例
        OkHttpClient client = new OkHttpClient();
        // 1,Get方式
        // 创建一个request
        Request request = new Request.Builder().url("").build();
        // 创建一个call
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 请求成功
                response.body().string();
            }
        });
        // 2,Post方式
        // 使用FormBody来创建RequestBody
        RequestBody body = new FormBody.Builder().add("", "").build();
        Request postReq = new Request.Builder().post(body).url("").build();
        Call postCall = client.newCall(postReq);
        postCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        // 3,上传文件
        //定义上传文件类型
        MediaType type = MediaType.parse("");
        //获取上传文件的对象
        File file = new File("");
        //创建request
        Request fileRequest = new Request.Builder().url("").post(RequestBody.create(type, file)).build();
        client.newCall(fileRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
        // 4,下载文件
        // 下载文件比较简单 通过返回的ResponseBody获取到文件流，现在到本地即可
        client.newCall(new Request.Builder().url("").build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //在此处进行下载处理
                InputStream inputStream = response.body().byteStream();
                FileOutputStream outputStream = null;


                outputStream = new FileOutputStream(new File(""));
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
            }
        });
        // 5,对超时时间与缓存的设置
        // 首先获取缓存位置
        File sdCache = getExternalCacheDir();
        // 缓存大小
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient cacheClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(), cacheSize))
                .build();

        // 6,多种数据同时上传

        RequestBody multiBody = new MultipartBody.Builder()
                .addFormDataPart("", "")
                .addFormDataPart("", "")
                .addFormDataPart("", "", RequestBody.create(type, file))
                .build();

        Request multiRequest = new Request.Builder().url("").post(multiBody).build();

    }
}
