package com.panda.test.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.panda.test.R;
import com.panda.test.classify.FragmentClassify;
import com.panda.test.community.FragmentCommunity;
import com.panda.test.home.FragmentHome;
import com.panda.test.shopping.FragmentShopping;
import com.panda.test.user.FragmentUser;

/**
  *
  * @author: huwenshuai
  * @date:   2017/4/24
  * @action:
 *
  */
public class MainActivity extends FragmentActivity {

    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_classify;
    private RadioButton rb_community;
    private RadioButton rb_shopping;
    private RadioButton rb_user;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private Fragment fragment;
    private FragmentHome fg_home;
    private FragmentClassify fg_classify;
    private FragmentCommunity fg_community;
    private FragmentShopping fg_shopping;
    private FragmentUser fg_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        judgeOrNull();
    }

    //判断是否为空
    private void judgeOrNull() {
        /**首先显示首页
         * 判断是否为空
         */
        if (fg_home == null) {
            //不为空就实例化
            fg_home = new FragmentHome();
        }
        //调用方法
        addFragment(fg_home);
        //RadioGroup选择
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home_main:
                        if (fg_home == null) {
                            fg_home = new FragmentHome();
                        }
                        addFragment(fg_home);
                        break;

                    case R.id.rb_classify_main:
                        if (fg_classify == null) {
                            fg_classify = new FragmentClassify();
                        }
                        addFragment(fg_classify);
                        break;

                    case R.id.rb_community_main:
                        if (fg_community == null) {
                            fg_community = new FragmentCommunity();
                        }
                        addFragment(fg_community);
                        break;

                    case R.id.rb_shopping_main:
                        fg_shopping = new FragmentShopping();
                        addFragment(fg_shopping);
                        break;

                    case R.id.rb_user_main:
                        if (fg_user == null) {
                            fg_user = new FragmentUser();
                        }
                        addFragment(fg_user);
                        break;
                }
            }
        });
    }

    //初始化控件
    private void initView() {
        frameLayout = (FrameLayout) findViewById(R.id.frame_main);
        radioGroup = (RadioGroup) findViewById(R.id.rg_main);
        rb_home = (RadioButton) findViewById(R.id.rb_home_main);
        rb_classify = (RadioButton) findViewById(R.id.rb_classify_main);
        rb_community = (RadioButton) findViewById(R.id.rb_community_main);
        rb_shopping = (RadioButton) findViewById(R.id.rb_shopping_main);
        rb_user = (RadioButton) findViewById(R.id.rb_user_main);
        rb_home.setChecked(true);
    }

    //添加fragment
    private void addFragment(Fragment fg) {
        //得到管理类
        fm = getSupportFragmentManager();
        //开启事务
        transaction = fm.beginTransaction();
        //赋值
        if (fragment != null) {
            transaction.hide(fragment);
        }
        //判断是否被Add过,没有就添加
        if (!fg.isAdded()) {
            transaction.add(R.id.frame_main, fg);
        }
        //有的话就显示
        transaction.show(fg);
        transaction.commit();
        //让后把当前的页面赋给fragment标签
        fragment = fg;
    }


    //再按退出
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() ==
                KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
