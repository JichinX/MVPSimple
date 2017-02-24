package com.example.crashhandler;

import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 替代系统的错误收集handler，改为自己收集处理 并上传
 * 单例
 * Created by Administrator on 2017/2/20.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler handler = null;
    private Context context;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private JSONObject errInfo;
    private Class restartActivity;
    private String url;


    private LogManager logManager;
    private LogUtils logUtils;

    private CrashHandler() {
        errInfo = new JSONObject();
    }

    public static CrashHandler newInstance() {
        if (null == handler) {
            handler = new CrashHandler();
        }
        return handler;
    }

    /**
     * 初始化 基本信息
     *
     * @param context         上下文
     * @param url             服务器地址
     * @param restartActivity 重启到目的Activity
     */
    public void init(Context context, String url, Class restartActivity) {

        this.context = context.getApplicationContext();
        this.restartActivity = restartActivity;
        this.url = url;

        logManager = LogManager.newInstance(context);
        logManager.checkLog();

        logUtils = LogUtils.getInstance();
        logUtils.setIsDeBug(true);

        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (!handleException(e) && null != defaultHandler) {
            defaultHandler.uncaughtException(t, e);
        } else {
            restartApp();
        }
    }

    /**
     * 做四件事 关于日志的上传时机 可灵活处置
     * 1，获取设备信息
     * 2，获取错误信息
     *
     * @param e
     * @return
     */
    private boolean handleException(Throwable e) {
        if (null == e) {
            return false;
        }

        try {
            getDeviceInfo();
//            getExceptionInfo(e);
//            logManager.saveExceptionLog(errInfo.toString());
        } catch (JSONException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 重启App
     */
    private void restartApp() {
        Intent intent = new Intent(context, restartActivity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid()); //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }

    /**
     * 获取错误信息
     *
     * @throws JSONException json 错误
     */
    private void getExceptionInfo(Throwable e) throws JSONException {
        StringBuilder builder = new StringBuilder("errInfo:");
        for (StackTraceElement traceElement : e.getStackTrace()) {
            builder.append("\tat ").append(traceElement).append("\n");
        }
        String str = errInfo.optString("content");
        builder.append(str, 0, str.length());
        errInfo.put("content", builder.toString());
    }

    /**
     * 获取设备信息
     *
     * @throws JSONException Json错误
     */
    private void getDeviceInfo() throws JSONException {
        String deviceInfo = DeviceUtils.newInstance().getDeviceInfo(context, errInfo);
        StringBuilder builder = new StringBuilder(deviceInfo);
        errInfo.put("content", builder.append("\n").toString());
    }

    /**
     * 获取服务器地址
     *
     * @return 服务器地址
     */
    String getUrl() {
        return url;
    }

    /**
     * 是否是调试模式，
     * 调试模式将显示Log
     *
     * @param b
     */
    public void setIsDeBug(boolean b) {

    }
}
