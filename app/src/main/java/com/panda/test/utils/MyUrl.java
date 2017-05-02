package com.panda.test.utils;

/**
  *
  * @author: huwenshuai
  * @date:   2017/4/26
  * @action: 接口地址
  */
public class MyUrl {
    //数据头
    private static String MYHERDER = "http://169.254.76.149:8080/DSShop";

    //json
    private static String MYJSON = MYHERDER+"/json";
    //url
    private static String MYIMG = MYHERDER+"/img";

    //home页数据地址
    public static String HOME_URL = MYJSON+"/HOME_URL.json";
    //home页图片数据地址
    public static String HOME_IMG_URL = MYIMG;


}
