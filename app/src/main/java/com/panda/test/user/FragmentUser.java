package com.panda.test.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panda.test.R;
import com.panda.test.base.BaseFragment;

/**
  *
  * @author: huwenshuai
  * @date:   2017/4/24
  * @action: 用户页
  */
public class FragmentUser extends BaseFragment{

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(mContext, R.layout.fragment_user,null);
        return view;
    }

    @Override
    public void initData() {

    }
}
