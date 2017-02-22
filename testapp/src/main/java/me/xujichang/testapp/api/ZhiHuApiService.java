package me.xujichang.testapp.api;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import me.xujichang.testapp.bean.ZhiHuNewsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 知乎日报 使用的请求接口
 * Created by Administrator on 2017/2/17.
 */

public interface ZhiHuApiService {
    /**
     * 获取知乎日报的API版本
     *
     * @param version 当前版本
     */
    @GET("version/android/{version}")
    Call<JsonObject> getVersion(@Path("version") String version);

    /**
     * 获取最新消息
     *
     * @return
     */
    @GET("news/latest")
    Observable<ZhiHuNewsBean> getLatestNews();
}
