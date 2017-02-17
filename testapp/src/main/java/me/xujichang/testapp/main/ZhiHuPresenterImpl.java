package me.xujichang.testapp.main;

/**
 * Created by Administrator on 2017/2/17.
 */

public class ZhiHuPresenterImpl implements MainContract.ZhiHuPresenter {

    private MainContract.ZhiHuView view;
    private ZhiHuDataSource dataSource;


    public ZhiHuPresenterImpl(MainContract.ZhiHuView view, ZhiHuDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
        view.setPresenter(this);
    }

    @Override
    public void loadZhiHuInformation() {
        dataSource.loadZhiHuData(new ZhiHuDataSource.LoadCallBack() {
            @Override
            public void onLoadZhiHuData() {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void start() {
        dataSource.initZhiHuData(new ZhiHuDataSource.InitCallBack() {
            @Override
            public void onInitZhiHuData() {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
