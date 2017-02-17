package com.example.rxandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnRx;
    private Context context;
    private EditText etRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        btnRx = (Button) findViewById(R.id.rx_btn);
        etRx = (EditText) findViewById(R.id.rx_et);
        //防止快速点击
        RxView.clicks(btnRx).throttleFirst(2, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                //执行按钮的点击事件
                Log.v(TAG, "点击了按钮");
            }
        });
        //长按
        RxView.longClicks(btnRx).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                //长按
                Log.v(TAG, "按钮长按");
            }
        });
        RxTextView.textChanges(etRx).debounce(1000, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return !TextUtils.isEmpty(charSequence.toString().trim());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        Toast.makeText(context, charSequence, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
