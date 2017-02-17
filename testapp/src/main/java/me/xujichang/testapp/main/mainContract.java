package me.xujichang.testapp.main;

import me.xujichang.testapp.base.BasePresenter;
import me.xujichang.testapp.base.BaseView;

/**
 * 主页所使用的中间人
 * Created by Administrator on 2017/2/17.
 */

public class MainContract {

    interface ZhiHuView extends BaseView<ZhiHuPresenter> {

    }

    interface ZhiHuPresenter extends BasePresenter {

        void loadZhiHuInformation();


    }

    interface DouBanBookView extends BaseView<DouBanBookPresenter> {

    }

    interface DouBanBookPresenter extends BasePresenter {

        void initDouBanBooksInformation();


    }

    interface DouBanMovieView extends BaseView<DouBanMoviePresenter> {

    }

    interface DouBanMoviePresenter extends BasePresenter {


        void initDouBanMoviesInformation();


    }

    interface LocalWeatherView extends BaseView<LocalWeatherPresenter> {

    }

    interface LocalWeatherPresenter extends BasePresenter {

        void initLocalWeatherInformation();

    }
}
