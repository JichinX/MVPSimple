package me.xujichang.testapp.login;

/**
 * Created by Administrator on 2017/2/17.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;
    private LoginDataSource dataSource;

    public LoginPresenter(LoginContract.View loginView, LoginDataSource dataSource) {
        this.loginView = loginView;
        this.dataSource = dataSource;
//        loginView.setPresenter(this);
    }

    @Override
    public void login(String name, String pwd) {
        dataSource.login(name, pwd, new LoginDataSource.LoginResultCallBack() {
            @Override
            public void onLoginSuccess() {
                loginView.loginSuccess();
            }

            @Override
            public void onLoginFail() {
                loginView.loginFail();
            }
        });
    }

    @Override
    public void start() {

    }
}
