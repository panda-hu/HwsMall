package com.panda.test.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.panda.test.R;
import com.panda.test.home.bean.HomeBean;
import com.panda.test.utils.MyUrl;

import java.util.List;

/**
 * author:huweshuai
 * time:2017/4/27
 */

public class ActivityAdapter extends PagerAdapter{

    private Context mContext;
    private List<HomeBean.ResultBean.ActInfoBean> mAct_info;
    public ActivityAdapter(Context context, List<HomeBean.ResultBean.ActInfoBean> act_into) {
        this.mContext=context;
        this.mAct_info=act_into;
    }

    @Override
    public int getCount() {
        return mAct_info.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=View.inflate(mContext, R.layout.item_act_home,null);
        ImageView vp_home_iv = (ImageView) view.findViewById(R.id.iv_act_home);
        Glide.with(mContext).load(MyUrl.HOME_IMG_URL+mAct_info.get(position).getIcon_url()).into(vp_home_iv);
        container.addView(view);
        return view;
    }
}
