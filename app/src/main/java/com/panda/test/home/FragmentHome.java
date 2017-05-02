package com.panda.test.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.panda.test.R;
import com.panda.test.base.BaseFragment;
import com.panda.test.home.adapter.FragmentHomeAdapter;
import com.panda.test.home.bean.HomeBean;
import com.panda.test.utils.MyUrl;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * @author: huwenshuai
 * @date: 2017/4/24
 * @action: 主页
 */
public class FragmentHome extends BaseFragment implements View.OnClickListener {

    private View view;
    private ImageView image_up_main;
    private ImageView titlebar_scan_home;
    private static final int REQUEST_CODE = 1;
    private RecyclerView rv_home;
    private TextView titlebar_search_home;
    private TextView titlebar_news_home;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(mContext, R.layout.fragment_home, null);
        return view;
    }

    @Override
    public void initData() {
        //标题栏的相关控件
        image_up_main = (ImageView) view.findViewById(R.id.image_up_home);
        titlebar_scan_home = (ImageView) view.findViewById(R.id.titlebar_scan_home);
        titlebar_search_home = (TextView) view.findViewById(R.id.titlebar_search_home);
        titlebar_news_home = (TextView) view.findViewById(R.id.titlebar_news_home);
        //找到RecyclerView
        rv_home = (RecyclerView) view.findViewById(R.id.rv_main);
        //点击事件
        initListener();
        //使用okhttp工具类get请求网络
        okhttpGetData();
    }

    //点击事件
    private void initListener() {
        titlebar_scan_home.setOnClickListener(this);
        titlebar_search_home.setOnClickListener(this);
        titlebar_news_home.setOnClickListener(this);
        image_up_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.titlebar_scan_home:
                Intent intent = new Intent(mContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;

            case R.id.titlebar_search_home:
                break;

            case R.id.titlebar_news_home:
                break;

            case R.id.image_up_home:
                rv_home.scrollToPosition(0);
                break;
        }
    }

    //okhttp工具类
    private void okhttpGetData() {
        String home_url= MyUrl.HOME_URL;
        OkHttpUtils.get()               //设置请求方式
                   .url(home_url)       //设置请求网址,后期更改方便
                   .build()
                   .execute(new StringCallback() {
                       //请求失败的回调
                       @Override
                       public void onError(Call call, Exception e, int id) {

                           Toast.makeText(mContext, "网络连接失败", Toast.LENGTH_SHORT).show();
                       }
                       //请求成功的回调
                       @Override
                       public void onResponse(String response, int id) {
                           //测试请求网络操作是否成功
                           Toast.makeText(mContext, "网络连接成功", Toast.LENGTH_SHORT).show();
                           //json解析数据
                           processprocessData(response);
                       }
                   });
    }

    private void processprocessData(String response) {
        // 1.json串  2.bean类
        HomeBean homeBean = JSON.parseObject(response, HomeBean.class);
        //获得数据
        HomeBean.ResultBean result = homeBean.getResult();
        if(result!=null){
            rv_home.setLayoutManager(new LinearLayoutManager(mContext));
            FragmentHomeAdapter homeAdapter=new FragmentHomeAdapter(getActivity(),result);
            rv_home.setAdapter(homeAdapter);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
