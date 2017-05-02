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
 * time:2017/4/26
 */

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeBean.ResultBean.ChannelInfoBean> mChannellist;
    public ChannelAdapter(Context context, List<HomeBean.ResultBean.ChannelInfoBean> channellist) {
        this.mContext=context;
        this.mChannellist=channellist;
    }

    //个数
    @Override
    public int getCount() {
        return mChannellist.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ChannelViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ChannelViewHolder();
            convertView=View.inflate(mContext, R.layout.item_channel_home,null);
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.iv_channel_home);
            viewHolder.tv_title= (TextView) convertView.findViewById(R.id.tv_channel_home);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ChannelViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(mChannellist.get(position).getChannel_name());
        Glide.with(mContext).load(MyUrl.HOME_IMG_URL+mChannellist.get(position).getImage()).into(viewHolder.iv_icon);
        return convertView;
    }
    class ChannelViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
