package me.xujichang.testapp.base;

import android.app.Application;
import android.util.Log;

import com.example.crashhandler.CrashHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import me.xujichang.testapp.login.LoginActivity;
import me.xujichang.testapp.util.Const;

/**
 * 使用自定义的Application  初始化全局的配置
 * Created by Administrator on 2017/2/20.
 */

public class APPApplication extends Application {
    private static final String TAG = "CrashHandler";

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化崩溃日志收集器
        CrashHandler handler = CrashHandler.newInstance();
        handler.init(getApplicationContext(), Const.DefaultData.BASE_URL, LoginActivity.class);
        new Thread() {
            @Override
            public void run() {
                test(Const.DefaultData.BASE_URL);
            }
        }.start();

    }


    private void test(String url) {
        Log.i(TAG, "下载服务器上日志:\n");
        HttpURLConnection connection = null;
        PrintWriter writer = null;
        try {
            URL realUrl = new URL(url);
            connection = (HttpURLConnection) realUrl.openConnection();
            connection.connect();

            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {

                InputStream is = connection.getInputStream();
                StringBuffer out = new StringBuffer();
                byte[] b = new byte[4096];
                for (int n; (n = is.read(b)) != -1; ) {
                    out.append(new String(b, 0, n));
                }
                Log.i(TAG, "服务器的日志信息------------------------success" + out.toString());
            } else {
                Log.i(TAG, "服务器的日志信息查询失败------------------------fail:" + response);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                connection.disconnect();
            }
            if (null != writer) {
                writer.close();
            }
        }
    }
}
