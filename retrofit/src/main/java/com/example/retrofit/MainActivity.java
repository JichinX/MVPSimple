package com.example.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.retrofit.interceptor.SelfInterceptor;
import com.example.retrofit.response.Contributor;
import com.example.retrofit.service.GetContributorsService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnRequest;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = (Button) findViewById(R.id.btn_request);

        //创建Retrofit实例 并配置基本设置
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new SelfInterceptor()).build())
                .build();
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContributors(retrofit);
            }
        });
    }

    private void getContributors(Retrofit retrofit) {
        //创建请求的服务 并设置参数  获取call实例
        GetContributorsService service = retrofit.create(GetContributorsService.class);
        Call<List<Contributor>> call = service.getContributors("square", "retrofit");

//        //执行请求
//        try {
//            //同步请求
//            Response<List<Contributor>> response = call.execute();
//            if (response.isSuccessful()) {
//                Log.v(TAG, "同步请求成功：" + response.toString());
//            } else {
//                Log.v(TAG, "同步请求失败：" + response.errorBody().toString());
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //克隆一个请求
//        Call<List<Contributor>> call1 = call.clone();
        //异步请求
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                //请求成功
                Log.v(TAG, "异步请求成功：" + response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                //请求失败
                Log.v(TAG, "异步请求失败：" + t.toString());
            }
        });
    }
}
