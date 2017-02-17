package me.xujichang.testapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.xujichang.testapp.R;

/**
 * APP的主页
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initDefaultFragment();
    }

    private void initDefaultFragment() {
        //加载默认的Fragment 即知乎日报
        ZhiHuFragment zhiHuFragment = ZhiHuFragment.newInstance();
        MainContract.ZhiHuPresenter presenter = new ZhiHuPresenterImpl(zhiHuFragment, new ZhiHuDataSource());
    }
}
