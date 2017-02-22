package me.xujichang.testapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.xujichang.testapp.util.ToastUtils;

/**
 * Activity基类，
 * Created by Administrator on 2017/2/17.
 */

public class BaseActivity extends AppCompatActivity {
    private ToastUtils utils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utils = ToastUtils.newInstence().setContext(this);
    }

    protected void toastShow(String msg) {
        utils.showToast(msg);
    }
}
