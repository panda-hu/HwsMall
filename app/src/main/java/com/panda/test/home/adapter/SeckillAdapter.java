package com.panda.test.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.panda.test.R;
import com.panda.test.home.bean.HomeBean;
import com.panda.test.utils.MyUrl;

import java.util.List;

/**
 * author:huweshuai
 * time:2017/4/27
 */


public class SeckillAdapter extends RecyclerView.Adapter<SeckillAdapter.SeckillAdapterViewHolder>{

    private final Context mContext;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> mlist;
    private SeckillAdapterViewHolder holder;

    public SeckillAdapter(Context context, List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext=context;
        this.mlist=list;
    }

    @Override
    public SeckillAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(mContext,R.layout.item_seckill_home,null);
        holder = new SeckillAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SeckillAdapterViewHolder holder, int position) {
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = mlist.get(position);
        Glide.with(mContext).load(MyUrl.HOME_IMG_URL+listBean.getFigure()).into(holder.iv_seckill_home);
        holder.tv_current_seckill_home.setText("¥ "+listBean.getCover_price());
        holder.tv_original_seckill_home.setText("¥ "+listBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    //设置item的监听事件
    private OnSeckillItemClick onSeckillItemClick;

    public void setOnSeckillItemClick(OnSeckillItemClick onSeckillItemClick){
        this.onSeckillItemClick=onSeckillItemClick;
    }

    //提供item监听接口
    public interface  OnSeckillItemClick{
        void OnItemClick(int position);
    }

    class SeckillAdapterViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv_seckill_home;
        private final TextView tv_current_seckill_home;
        private final TextView tv_original_seckill_home;

        public SeckillAdapterViewHolder(View itemView) {
            super(itemView);
            iv_seckill_home = (ImageView) itemView.findViewById(R.id.iv_seckill_home);
            tv_current_seckill_home = (TextView) itemView.findViewById(R.id.tv_current_seckill_home);
            tv_original_seckill_home = (TextView) itemView.findViewById(R.id.tv_original_seckill_home);
            tv_original_seckill_home.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSeckillItemClick != null){
                        onSeckillItemClick.OnItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
