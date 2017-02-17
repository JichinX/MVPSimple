package com.example.retrofit.service;

import com.example.retrofit.response.Contributor;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface GetContributorsService {
    //@Path 占位符,拼装URl
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repo);

    //@HTTP 设置完整的请求链接 与请求方式
    @HTTP(method = "get", path = "/repos/{owner}/{repo}/contributors", hasBody = false)
    Call<List<Contributor>> getContributions(@Path("owner") String owner, @Path("repo") String repo);

    //@Streaming 数据流 用于下载文件
    @Streaming
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> methord1(@Path("owner") String owner, @Path("repo") String repo);

    //@Url
    Call<List<Contributor>> methord2(@Url String url);

    //@Query 用于get请求的时候，在请求链接后面添加的参数
    @GET("group/users")
    Call<List<Contributor>> methord3(@Query("url") String url);// 等同于 @GET(group/users?url=url);

    //@Field 提交表单数据 与 @FormUrlEncoded配合使用
    @FormUrlEncoded
    @POST("user/edit")
    Call<List<Contributor>> methord4(@Field("first_name") String first, @Field("last_name") String last);

    //@Part 上传多种数据 配合 @Multipart使用
    @Multipart
    @POST("user/edit")
    Call<List<Contributor>> methord5(@Part("first_part") String first, @Part("last_part") String last);

    //@Header 用来修饰方法的参数 是作为Header使用的
    @GET("group/users")
    Call<List<Contributor>> methord6(@Header("first_header") String first, @Header("last_header") String last);

    //@Headers 是固定在请求信息里面的 用来修饰这个请求的方法。上面的请求 等同于
    @Headers({
            "first-header:first",
            "last_header:last"
    })
    @GET("group/users")
    Call<List<Contributor>> methord7();

    @POST("group/users")
    Call<List<Contributor>> methord8(@Body RequestBody body);
}
