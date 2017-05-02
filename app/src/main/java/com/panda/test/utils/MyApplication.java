package com.panda.test.utils;

import android.app.Application;
import android.content.Context;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
  *
  * @author: huwenshuai
  * @date:   2017/4/25
  * @action: application
  */

public class MyApplication extends Application {

    private static Context mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mcontext=this;
        //初始化okHttp工具类
        initok();
        //初始化二维码Zxing
        initZxing();
    }

    //获得全局上下文
    public static Context getContext(){
        return mcontext;
    }

    private void initZxing() {
        ZXingLibrary.initDisplayOpinion(this);
    }

    private void initok() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(new LoggerInterceptor("TAG"))
                //设置连接超时
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //设置读取超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        //进行okhttp工具类初始化的操作
        OkHttpUtils.initClient(okHttpClient);
    }
}
