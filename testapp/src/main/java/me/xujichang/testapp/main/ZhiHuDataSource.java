package me.xujichang.testapp.main;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import me.xujichang.testapp.api.ZhiHuApiService;
import me.xujichang.testapp.api.ZhiHuRetrofitManager;
import me.xujichang.testapp.base.BaseDataSource;
import me.xujichang.testapp.bean.ZhiHuNewsBean;


/**
 * 知乎日报的Module
 * Created by Administrator on 2017/2/17.
 */
public class ZhiHuDataSource implements BaseDataSource {


    public void initZhiHuData(final InitCallBack callBack) {
        ZhiHuRetrofitManager.newInstance()
                .getRetrofit()
                .create(ZhiHuApiService.class)
                .getLatestNews()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ZhiHuNewsBean>() {
                    @Override
                    public void onNext(ZhiHuNewsBean zhiHuNewsBean) {
                        callBack.onInitZhiHuData(zhiHuNewsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onDataNotAvailable();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        call.enqueue(new Callback<ZhiHuNewsBean>() {
//            @Override
//            public void onResponse(Call<ZhiHuNewsBean> call, Response<ZhiHuNewsBean> response) {
//                callBack.onInitZhiHuData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<ZhiHuNewsBean> call, Throwable t) {
//                callBack.onDataNotAvailable();
//            }
//        });
    }

    public void loadZhiHuData(LoadCallBack callBack) {

    }

    public interface InitCallBack {
        void onInitZhiHuData(ZhiHuNewsBean o);

        void onDataNotAvailable();
    }

    public interface LoadCallBack {
        void onLoadZhiHuData();

        void onDataNotAvailable();
    }
}
