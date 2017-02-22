package me.xujichang.testapp.temp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import me.xujichang.testapp.login.LoginActivity;

/**
 * 替代系统的错误收集handler，改为自己收集处理 并上传
 * 单例
 * Created by Administrator on 2017/2/20.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static CrashHandler handler = null;
    private Context context;
    private Thread.UncaughtExceptionHandler defaultHandler;
    private JSONObject errInfo;

    private CrashHandler() {
        errInfo = new JSONObject();
    }

    public static CrashHandler newInstance() {
        if (null == handler) {
            handler = new CrashHandler();
        }
        return handler;
    }

    public void init(Context context) {
        this.context = context;
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        defaultHandler.uncaughtException(t, e);

        if (!handleException(e) && null != defaultHandler) {
            defaultHandler.uncaughtException(t, e);
        } else {
            restartApp();
        }
    }

    private boolean handleException(Throwable e) {
        if (null == e) {
            return false;
        }
        //做四件事 关于日志的上传时机 可灵活处置
        // 1，获取收集信息
        // 2，获取错误信息
        // 3，上传服务器
        // 4，重启APP
        try {
            getExceptionInfo(e);
            getDeviceInfo();
            SendInfoToServer();
        } catch (JSONException ex) {
            ex.printStackTrace();
        } finally {
        }

        return true;
    }

    /**
     * 重启App
     */
    private void restartApp() {
        Log.i(TAG, "重启App");
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid()); //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }

    /**
     * 上传日志信息至服务器
     */
    private void SendInfoToServer() {
        Log.i(TAG, "上传日志信息至服务器:\n" + errInfo.toString());

    }

    /**
     * 获取错误信息
     */
    private void getExceptionInfo(Throwable e) throws JSONException {
        Log.i(TAG, "获取错误信息------------------------");
        StringBuilder builder = new StringBuilder(e.toString() + "\n");
        for (StackTraceElement traceElement : e.getStackTrace())
            builder.append("\tat ").append(traceElement).append("\n");
        errInfo.put("ExceptionInfo", builder.toString());
    }

    /**
     * 获取设备信息
     * <p>
     * IEM
     * cpu
     * Android 版本号
     * 系统类型
     * 手机厂商
     * 手机型号
     */
    private void getDeviceInfo() throws JSONException {
        Log.i(TAG, "获取设备信息----------------------");
        errInfo.put("deviceInfo", DeviceUtils.newInstence().getCpuInfo());
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);


    }
}
