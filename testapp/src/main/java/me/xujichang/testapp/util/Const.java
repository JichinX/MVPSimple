package me.xujichang.testapp.util;

/**
 * Created by Administrator on 2017/2/17.
 */

public class Const {

    public static class DefaultData {
        public static final String DEFAULT_NAME = "18758249744";
        public static final String DEFAULT_PWD = "12345678";
    }

    public static class Environment {
        /**
         * 开发环境
         * 默认为不获取崩溃日志 1
         */
        public static final int ENVIRONMENT = 1;

        /**
         * 获取崩溃日志
         */
        public static final int ENVIRONMENT_EXCEPTION = 0;
        /**
         * 正常开发环境 不需要获取崩溃日志，便于调试
         */
        public static final int ENVIRONMENT_DEVELOPER = 1;

    }
}
