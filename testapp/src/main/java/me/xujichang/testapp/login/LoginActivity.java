package me.xujichang.testapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.xujichang.testapp.main.MainActivity;
import me.xujichang.testapp.R;
import me.xujichang.testapp.base.BaseActivity;

/**
 * 负责登录功能的Activity
 * Created by Administrator on 2017/2/17.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_login);

        presenter = new LoginPresenter(this, new LoginDataSource());

        //控件实例化
        Button btnLogin = (Button) findViewById(R.id.btn_login_submit);
        final EditText etName = (EditText) findViewById(R.id.et_login_name);
        final EditText etPwd = (EditText) findViewById(R.id.et_login_pwd);

        //监听事件
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String pwd = etPwd.getText().toString();
                //判空
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    toastShow("用户名或密码不能为空");
                    return;
                }
                presenter.login(name, pwd);
            }
        });
    }

    @Override
    public void loginSuccess() {
        toastShow("登陆成功");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFail() {
        toastShow("登陆失败");
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
