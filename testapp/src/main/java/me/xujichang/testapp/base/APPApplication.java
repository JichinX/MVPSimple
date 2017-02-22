package me.xujichang.testapp.base;

import android.app.Application;

import com.example.crashhandler.CrashHandler;

import me.xujichang.testapp.login.LoginActivity;
import me.xujichang.testapp.util.Const;

/**
 * 使用自定义的Application  初始化全局的配置
 * Created by Administrator on 2017/2/20.
 */

public class APPApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化崩溃日志收集器
//        CrashHandler handler = CrashHandler.newInstance();
//        handler.init(getApplicationContext(), LoginActivity.class);
    }
}
