package me.xujichang.testapp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/2/17.
 */

public class ToastUtils {

    private static ToastUtils toastUtils;
    private Context context;

    private static class ClassHolder {
        public static ToastUtils toast = new ToastUtils();
    }

    private ToastUtils() {

    }

    public static ToastUtils newInstence() {
        if (null == toastUtils) {
            toastUtils = ClassHolder.toast;
        }
        return toastUtils;
    }

    public ToastUtils setContext(Context context) {
        this.context = context;
        return toastUtils;
    }

    public void showToast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

}
