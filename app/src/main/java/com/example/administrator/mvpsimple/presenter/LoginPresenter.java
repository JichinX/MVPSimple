package com.example.administrator.mvpsimple.presenter;


import com.example.administrator.mvpsimple.imodule.ILoginModule;
import com.example.administrator.mvpsimple.ipresenter.ILoginPresenter;
import com.example.administrator.mvpsimple.iview.ILoginView;
import com.example.administrator.mvpsimple.module.LoginModule;

/**
 * Created by Administrator on 2017/2/13.
 */

public class LoginPresenter implements ILoginPresenter {
    /**
     * 维护一个View  对象
     */
    private ILoginView view;
    /**
     * 维护一个module对象
     */
    private ILoginModule module;

    /**
     * 实例化的同时 传入维护的module对象 与view 对象
     *
     * @param view view对象
     */
    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.module = new LoginModule(this);
    }

    @Override
    public void login(String name, String pwd) {
        module.login(name, pwd);
    }

    @Override
    public void loginSuccess(String msg) {
        view.showSuccessToast(msg);
    }

    @Override
    public void loginFail(String msg) {
        view.showFailToast(msg);
    }
}
