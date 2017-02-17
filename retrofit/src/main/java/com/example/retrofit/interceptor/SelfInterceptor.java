package com.example.retrofit.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 自定义拦截器
 * Created by Administrator on 2017/2/16.
 */

public class SelfInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {


        return null;
    }
}
