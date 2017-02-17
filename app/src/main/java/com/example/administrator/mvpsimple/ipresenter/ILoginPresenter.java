package com.example.administrator.mvpsimple.ipresenter;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface ILoginPresenter {
    /**登录
     * @param name 姓名
     * @param pwd 密码
     */
    void login(String name, String pwd);

    /**
     * 登陆成功
     * @param msg 成功的信息
     */
    void loginSuccess(String msg);

    /**
     * 登录失败
     * @param msg　失败信息
　   */
    void loginFail(String msg);
}
