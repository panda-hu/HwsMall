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
public class RecommendAdapter extends BaseAdapter{
    private final Context mcontext;
    private final List<HomeBean.ResultBean.RecommendInfoBean> mrecommend_info;

    public RecommendAdapter(Context context, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.mcontext=context;
        this.mrecommend_info=recommend_info;
    }

    @Override
    public int getCount() {
        return mrecommend_info.size();
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
        RecommendViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new RecommendViewHolder();
            convertView=convertView.inflate(mcontext, R.layout.item_recommend_home,null);
            viewHolder.iv= (ImageView) convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name_recommend);
            viewHolder.tv_price= (TextView) convertView.findViewById(R.id.tv_price_recommend);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (RecommendViewHolder) convertView.getTag();
        }
        Glide.with(mcontext).load(MyUrl.HOME_IMG_URL+mrecommend_info.get(position).getFigure()).into(viewHolder.iv);
        viewHolder.tv_name.setText(mrecommend_info.get(position).getName());
        viewHolder.tv_price.setText("￥"+mrecommend_info.get(position).getCover_price());
        //点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecommendItemClick != null){
                    onRecommendItemClick.OnItemClick(position);
                }
            }
        });
        return convertView;
    }
    class RecommendViewHolder{
        ImageView iv;
        TextView tv_name;
        TextView tv_price;
    }

    //设置item的监听事件
    private RecommendAdapter.OnRecommendItemClick onRecommendItemClick;

    public void setOnRecommendItemClick(RecommendAdapter.OnRecommendItemClick onRecommendItemClick){
        this.onRecommendItemClick=onRecommendItemClick;
    }

    //提供item监听接口
    public interface  OnRecommendItemClick{
        void OnItemClick(int position);
    }
}
