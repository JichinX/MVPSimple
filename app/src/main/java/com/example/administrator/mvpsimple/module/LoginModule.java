package com.example.administrator.mvpsimple.module;

import android.support.v4.text.TextUtilsCompat;
import android.text.TextUtils;

import com.example.administrator.mvpsimple.imodule.ILoginModule;
import com.example.administrator.mvpsimple.ipresenter.ILoginPresenter;

/**
 * Created by Administrator on 2017/2/13.
 */

public class LoginModule implements ILoginModule {
    /**
     * 维护一个Presenter
     */
    private ILoginPresenter presenter;
    private static final String DEFAULT_NAME = "default_name";
    private static final String DEFAULT_PWD = "default_pwd";

    /**
     * 实例化的同时 传入Presenter
     *
     * @param presenter presenter 对象
     */
    public LoginModule(ILoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login() {
        loginServer(DEFAULT_NAME, DEFAULT_PWD);
    }


    @Override
    public void login(String name) {
        loginServer(name, DEFAULT_PWD);
    }

    @Override
    public void login(String name, String pwd) {
        loginServer(name, pwd);
    }

    /**
     * 执行登录的操作
     */
    private void loginServer(String name, String pwd) {
        //模拟登录的过程
        if ("xujichang".equals(name) || "pwd".equals(pwd)) {
            presenter.loginSuccess("登陆成功");
        } else {
            presenter.loginFail("登录失败");
        }
    }

}
