package com.example.retrofit.response;

/**
 * Created by Administrator on 2017/2/15.
 */

public class Contributor {
    private String login;
    private int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
