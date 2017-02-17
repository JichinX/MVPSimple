package com.example.administrator.mvpsimple.imodule;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface ILoginModule {
    /**
     * 默认登录
     */
    void login();

    /**
     * 通过用户名登录 密码默认
     * @param name 用戶名
     */
    void login(String name);

    /**
     * 通过用户名与密码登录
     * @param name
     * @param pwd
     */
    void login(String name, String pwd);
}
