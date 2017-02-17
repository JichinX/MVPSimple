package com.example.administrator.mvpsimple;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.mvpsimple.iview.ILoginView;
import com.example.administrator.mvpsimple.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements ILoginView {
    private Context context;
    private LoginPresenter presenter;

    private EditText etName;
    private EditText etPwd;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        init();
    }

    private void init() {
        presenter = new LoginPresenter(this);
        //实例化控件
        etName = (EditText) findViewById(R.id.et_login_name);
        etPwd = (EditText) findViewById(R.id.et_login_pwd);
        btLogin = (Button) findViewById(R.id.bt_login);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击登录 的事件处理
                if (TextUtils.isEmpty(etName.getText())) {
                    showToast("用户名 不能为空");
                    return;
                }
                if (TextUtils.isEmpty(etPwd.getText())) {
                    showToast("密码 不能为空");
                    return;
                }
                String name = etName.getText().toString();
                String pwd = etPwd.getText().toString();

                presenter.login(name, pwd);
            }
        });
    }

    @Override
    public void showSuccessToast(String msg) {
        showToast(msg);
    }

    @Override
    public void showFailToast(String msg) {
        showToast(msg);
    }

    private void showToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
