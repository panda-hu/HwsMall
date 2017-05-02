package com.panda.test.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.panda.test.R;
import com.panda.test.home.adapter.FragmentHomeAdapter;
import com.panda.test.home.bean.GoodsBean;
import com.panda.test.home.view.MyAddSubView;
import com.panda.test.utils.MyUrl;

/**
 * author:huweshuai
 * time:2017/4/30
 */

public class GoodsShowActivity extends Activity implements View.OnClickListener {

    private WebView wb_good_info_more;
    private GoodsBean goodsBean;
    private ImageView iv_good_info_image;
    private TextView tv_good_info_name;
    private TextView tv_good_info_price;
    private Button btn_good_info_addcart;
    private PopupWindow popupWindow;
    private MyAddSubView add_sub_view;
    private ImageView iv_goodinfo_photo;
    private TextView tv_goodinfo_name;
    private TextView tv_goodinfo_price;
    private Button bt_goodinfo_cancel;
    private Button bt_goodinfo_confim;

    private String name;
    private String cover_price;
    private String figure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_show);
        initView();
    }

    private void initView() {
        //转换传过来的值为GoodsBean对象
        goodsBean = (GoodsBean) getIntent().getSerializableExtra("mgb");
        iv_good_info_image = (ImageView) findViewById(R.id.iv_good_info_image);
        tv_good_info_name = (TextView) findViewById(R.id.tv_good_info_name);
        tv_good_info_price = (TextView) findViewById(R.id.tv_good_info_price);
        wb_good_info_more = (WebView) findViewById(R.id.wb_good_info_more);
        btn_good_info_addcart = (Button) findViewById(R.id.btn_good_info_addcart);
        name = goodsBean.getName();
        cover_price = goodsBean.getCover_price();
        figure = goodsBean.getFigure();
        initData();
        initClick();
    }

    private void initClick() {
        btn_good_info_addcart.setOnClickListener(this);
    }

    private void initData() {
        //商品详情页控件赋值
        Glide.with(GoodsShowActivity.this).load(MyUrl.HOME_IMG_URL + figure).into(iv_good_info_image);
        tv_good_info_name.setText(name);
        tv_good_info_price.setText("￥" +cover_price);
        wb_good_info_more.loadUrl("http://www.baidu.com");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        wb_good_info_more.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = wb_good_info_more.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);

        //优先使用缓存
        wb_good_info_more.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    }

    private void showPopupWindow() {
        View v = View.inflate(GoodsShowActivity.this, R.layout.popupwindow_add, null);
        popupWindow = new PopupWindow(v, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        iv_goodinfo_photo = (ImageView) v.findViewById(R.id.iv_goodinfo_photo);
        tv_goodinfo_name = (TextView) v.findViewById(R.id.tv_goodinfo_name);
        tv_goodinfo_price = (TextView) v.findViewById(R.id.tv_goodinfo_price);
        //取消
        bt_goodinfo_cancel = (Button) v.findViewById(R.id.bt_goodinfo_cancel);
        //确定
        bt_goodinfo_confim = (Button) v.findViewById(R.id.bt_goodinfo_confim);

        //页面弹出popupwindow,给popupwindow中的控件赋值
        Glide.with(GoodsShowActivity.this).load(MyUrl.HOME_IMG_URL + figure).into(iv_goodinfo_photo);
        tv_goodinfo_name.setText(name);
        tv_goodinfo_price.setText("￥" +cover_price);


        //添加popupWindow中的加减自定义布局
        add_sub_view = (MyAddSubView) v.findViewById(R.id.add_sub_view);
        //设置popupWindow动画显示消失的动画效果
        popupWindow.setAnimationStyle(R.style.popu);
        //设置出现位置
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);

        //点击事件
        add_sub_view.setOnNumberChangerListener(new MyAddSubView.OnNumberChangerListener() {
            @Override
            public void OnNumberChanger(int value) {
                Toast.makeText(GoodsShowActivity.this, "当前值"+value, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btn_good_info_addcart:
                showPopupWindow();

                bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(GoodsShowActivity.this, "恭喜，添加购物车成功！", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                break;

        }
    }
}
