package me.xujichang.testapp.api;

import me.xujichang.testapp.base.BaseRetrofitManager;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 知乎日报使用的retrofit管理器
 * Created by Administrator on 2017/2/21.
 */

public class ZhiHuRetrofitManager extends BaseRetrofitManager {

    private static ZhiHuRetrofitManager manager;


    private ZhiHuRetrofitManager() {
        initRetrofit();
    }

    public static ZhiHuRetrofitManager newInstance() {
        if (null == manager) {
            manager = new ZhiHuRetrofitManager();
        }
        return manager;
    }

    @Override
    protected String getBASE_URL() {
        return "http://news-at.zhihu.com/api/4/";
    }

    @Override
    protected Converter.Factory getConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Override
    protected boolean isRxJavaSupported() {
        return true;
    }

    @Override
    protected OkHttpClient getClient() {
        return new OkHttpClient();
    }

}
