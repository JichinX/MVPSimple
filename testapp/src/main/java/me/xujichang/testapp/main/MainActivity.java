package me.xujichang.testapp.main;

import android.net.Uri;
import android.os.Bundle;

import me.xujichang.testapp.R;
import me.xujichang.testapp.base.BaseActivity;

/**
 * APP的主页
 */
public class MainActivity extends BaseActivity implements ZhiHuFragment.OnFragmentInteractionListener {

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

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, zhiHuFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
