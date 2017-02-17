package me.xujichang.testapp.login;

import me.xujichang.testapp.base.BasePresenter;
import me.xujichang.testapp.base.BaseView;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LoginContract {
    /**
     * 登录页面View
     */
    public interface View extends BaseView<Presenter> {
        void loginSuccess();

        void loginFail();
    }

    /**
     * 登录页面使用的中间人
     */
    public interface Presenter extends BasePresenter {
        /**
         * 执行登录
         */
        void login(String name, String pwd);
    }
}
