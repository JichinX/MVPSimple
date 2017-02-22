package me.xujichang.testapp.base;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * retrofit 管理器 使用单例 来保证Retrofit 的唯一
 * Created by Administrator on 2017/2/17.
 */

public abstract class BaseRetrofitManager {
    private Retrofit retrofit;
    /**
     * URL前缀
     */
    protected String BASE_URL;
    /**
     * 数据转换的方式
     */
    private Converter.Factory factory;

    private OkHttpClient okHttpClient;

    protected void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getBASE_URL())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(getConverterFactory())
                .client(getClient())
                .build();
    }


    /**
     * 获取请求使用的BaseUrl
     */
    protected abstract String getBASE_URL();

    /**
     * 获取数据转换使用的方式
     *
     * @return
     */
    protected abstract Converter.Factory getConverterFactory();

    /**
     * 是否使用RxJava方式
     *
     * @return
     */
    protected abstract boolean isRxJavaSupported();


    protected abstract OkHttpClient getClient();

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
