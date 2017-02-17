package me.xujichang.testapp.main;

import me.xujichang.testapp.base.BaseDataSource;

/**
 * Created by Administrator on 2017/2/17.
 */
public class ZhiHuDataSource implements BaseDataSource {


    public void initZhiHuData(InitCallBack callBack) {

    }

    public void loadZhiHuData(LoadCallBack callBack) {

    }

    public interface InitCallBack {
        void onInitZhiHuData();

        void onDataNotAvailable();
    }

    public interface LoadCallBack {
        void onLoadZhiHuData();

        void onDataNotAvailable();
    }
}
