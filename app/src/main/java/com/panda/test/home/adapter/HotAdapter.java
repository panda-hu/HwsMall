package com.panda.test.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.panda.test.R;
import com.panda.test.home.bean.HomeBean;
import com.panda.test.utils.MyUrl;

import java.util.List;

/**
 * author:huweshuai
 * time:2017/4/28
 */

public class HotAdapter extends BaseAdapter{
    private final Context mcontext;
    private final List<HomeBean.ResultBean.HotInfoBean> mhot_info;

    public HotAdapter(Context context, List<HomeBean.ResultBean.HotInfoBean> hot_info) {
        this.mcontext=context;
        this.mhot_info=hot_info;
    }

    @Override
    public int getCount() {
        return mhot_info.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HotViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new HotViewHolder();
            convertView=convertView.inflate(mcontext, R.layout.item_hot_home,null);
            viewHolder.iv= (ImageView) convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name_hot);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price_hot);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (HotViewHolder) convertView.getTag();
        }
        Glide.with(mcontext).load(MyUrl.HOME_IMG_URL+mhot_info.get(position).getFigure()).into(viewHolder.iv);
        viewHolder.tv_name.setText(mhot_info.get(position).getName());
        viewHolder.tv_price.setText(mhot_info.get(position).getCover_price());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onHotItemClick != null){
                    onHotItemClick.OnItemClick(position);
                }
            }
        });
        return convertView;
    }
    class HotViewHolder{
        ImageView iv;
        TextView tv_name;
        TextView tv_price;
    }

    //设置item的监听事件
    private HotAdapter.OnHotItemClick onHotItemClick;

    public void setOnHotItemClick(HotAdapter.OnHotItemClick onHotItemClick){
        this.onHotItemClick=onHotItemClick;
    }

    //提供item监听接口
    public interface  OnHotItemClick{
        void OnItemClick(int position);
    }
}
