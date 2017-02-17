package com.example.administrator.mvpsimple.iview;

/**
 * Created by Administrator on 2017/2/13.
 */

public interface ILoginView {

    /**
     * 显示登陆成功的信息
     *
     * @param msg 成功的信息
     */
    void showSuccessToast(String msg);

    /**
     * 登录失败的信息
     *
     * @param msg 失败的信息
     */
    void showFailToast(String msg);
}
