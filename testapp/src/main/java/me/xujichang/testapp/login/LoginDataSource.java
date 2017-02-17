package me.xujichang.testapp.login;

import android.support.annotation.NonNull;

import me.xujichang.testapp.base.BaseDataSource;
import me.xujichang.testapp.util.Const;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LoginDataSource implements BaseDataSource {

    void login(String name, String pwd, @NonNull LoginResultCallBack callBack) {

        if (name.trim().equals(Const.DefaultData.DEFAULT_NAME) && pwd.trim().equals(Const.DefaultData.DEFAULT_PWD)) {
            callBack.onLoginSuccess();
        } else {
            callBack.onLoginFail();
        }
    }

    public interface LoginResultCallBack {
        void onLoginSuccess();

        void onLoginFail();
    }

}
