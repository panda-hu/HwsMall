package com.panda.test.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.panda.test.R;
import com.panda.test.activity.GoodsShowActivity;
import com.panda.test.home.bean.GoodsBean;
import com.panda.test.home.bean.HomeBean;
import com.panda.test.utils.GlideImageLoader;
import com.panda.test.utils.MyUrl;
import com.youth.banner.Banner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: huwenshuai
 * @date: 2017/4/26
 * @action: Home页适配器
 */
public class FragmentHomeAdapter extends RecyclerView.Adapter {

    //广告条幅类型
    private static final int BANNER = 0;
    //频道类型
    private static final int CHANNEL = 1;
    //活动类型
    private static final int ACTIVITY = 2;
    //秒杀类型
    private static final int SECKILL = 3;
    //推荐类型
    private static final int RECOMMEND = 4;
    //热卖类型
    private static final int HOT = 5;
    //设置一个标签,接收类型,默认是轮播图
    private int currentType = BANNER;


    public  final static String SER_KEY = "mgb";

    private final LayoutInflater mInflater;
    private Context mContext;
    private HomeBean.ResultBean mResultBean;
    private List<String> banner_list;

    public FragmentHomeAdapter(Context context, HomeBean.ResultBean resultbean) {
        this.mContext = context;
        this.mResultBean = resultbean;
        mInflater = LayoutInflater.from(mContext);
    }

    //根据position判断类,并且返回数据
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;

            case CHANNEL:
                currentType = CHANNEL;
                break;

            case ACTIVITY:
                currentType = ACTIVITY;
                break;

            case SECKILL:
                currentType = SECKILL;
                break;

            case RECOMMEND:
                currentType = RECOMMEND;
                break;

            case HOT:
                currentType = HOT;
                break;

        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            //返回创建的bannerHolder
            BannerViewHolder bannerHolder = new BannerViewHolder(mInflater.inflate(R.layout.recyclerview_banner_home, null));
            return bannerHolder;

        } else if (viewType == CHANNEL) {
            //返回创建的channelHolder
            ChannelViewHolder channelHolder = new ChannelViewHolder(mContext, mInflater.inflate(R.layout.recyclerview_gridview_home, null));
            return channelHolder;

        } else if (viewType == ACTIVITY) {
            //返回创建的activityHolder
            ActivityViewHolder activityHolder = new ActivityViewHolder(mContext, mInflater.inflate(R.layout.recyclerview_activity_home, null));
            return activityHolder;

        } else if (viewType == SECKILL) {
            //返回创建的seckillHolder
            SeckillViewHolder seckillHolder = new SeckillViewHolder(mContext, mInflater.inflate(R.layout.recyclerview_seckill_home, null));
            return seckillHolder;

        } else if (viewType == RECOMMEND) {
            //返回创建的recommendHolder
            RecommendViewHolder recommendHolder = new RecommendViewHolder(mContext, mInflater.inflate(R.layout.recyclerview_recommend_home, null));
            return recommendHolder;

        } else if (viewType == HOT) {
            //返回创建的hotHolder

            HotViewHolder hotHolder = new HotViewHolder(mContext, mInflater.inflate(R.layout.recyclerview_hot_home, null));
            return hotHolder;
        }
        return null;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int myPosition = getItemViewType(position);
        if (myPosition == BANNER) {
            //转换类型
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //设置加载器
            bannerViewHolder.mBanner.setImageLoader(new GlideImageLoader());
            //调用方法获取数据
            bannerViewHolder.setBannerData(mResultBean.getBanner_info());
            //设置数据
            bannerViewHolder.mBanner.setImages(banner_list);
            //开启
            bannerViewHolder.mBanner.start();
        } else if (myPosition == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            //给频道的ViewHolder设置数据
            channelViewHolder.setChannelData(mResultBean.getChannel_info());
        } else if (myPosition==ACTIVITY){
            ActivityViewHolder activityViewHolder= (ActivityViewHolder) holder;
            activityViewHolder.setActivityData(mResultBean.getAct_info());
        } else if (myPosition==SECKILL){
            SeckillViewHolder seckillViewHolder= (SeckillViewHolder) holder;
            seckillViewHolder.setSeckillData(mResultBean.getSeckill_info());
        } else if (myPosition==RECOMMEND){
            RecommendViewHolder recommendViewHolder= (RecommendViewHolder) holder;
            recommendViewHolder.setRecommendData(mResultBean.getRecommend_info());
        } else if (myPosition==HOT){
            HotViewHolder hotViewHolder= (HotViewHolder) holder;
            hotViewHolder.setHotData(mResultBean.getHot_info());
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    //banner的viewHolder
    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Banner mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.banner_home);
        }

        //banner的设置数据方法
        public void setBannerData(List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            banner_list = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                banner_list.add(MyUrl.HOME_IMG_URL + banner_info.get(i).getImage());
            }
        }
    }


    //主页频道的的ViewHolder
    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private GridView gc_Channel;


        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            gc_Channel = (GridView) itemView.findViewById(R.id.gv_channel_home);

        }

        //gridview的设置数据方法
        public void setChannelData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            // 创建适配器对象
            ChannelAdapter mAdapter = new ChannelAdapter(mContext, channel_info);
            //设置给GridView的适配器
            gc_Channel.setAdapter(mAdapter);
        }
    }

    //主页ACT的的ViewHolder
    private class ActivityViewHolder extends RecyclerView.ViewHolder {

        private final ViewPager vp_home;

        public ActivityViewHolder(Context context, View itemView) {
            super(itemView);
            vp_home = (ViewPager) itemView.findViewById(R.id.vp_home);
        }

        //ActivityViewHolder的设置数据方法
        public void setActivityData(List<HomeBean.ResultBean.ActInfoBean> act_info) {
            ActivityAdapter activityAdapter=new ActivityAdapter(mContext, act_info);
            vp_home.setAdapter(activityAdapter);
        }
    }

    //主页Seckill的的ViewHolder
    private class SeckillViewHolder extends RecyclerView.ViewHolder {
        //倒计时的时间,从服务器那拿两个值,进行相减得到倒计时的真实数值.这里定义了个临时变量
        private long dt=0;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dt=dt-1000;
                //把拿到的秒值时间数据转换为小时,分,秒的形式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(new Date(dt));
                //把倒计时显示到UI上
                tv_tiem_home.setText(time);
                //在不断发送消息前先移除一下,减少OOM
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                //时间为0时,不进行相减
                if(dt <=0 ){
                    //把所有消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        private final RecyclerView rcv_seckill_home;
        private final TextView tv_tiem_home;
        private final TextView tv_gengduo_home;

        //构造方法
        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            rcv_seckill_home = (RecyclerView) itemView.findViewById(R.id.rcv_seckill_home);
            tv_tiem_home = (TextView) itemView.findViewById(R.id.tv_tiem_home);
            tv_gengduo_home = (TextView) itemView.findViewById(R.id.tv_gengduo_home);

        }

        //SeckillViewHolder的设置数据方法
        public void setSeckillData( HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            rcv_seckill_home.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list = seckill_info.getList();
            SeckillAdapter rcvAdatper=new SeckillAdapter(mContext, list);
            rcv_seckill_home.setAdapter(rcvAdatper);
            rcvAdatper.setOnSeckillItemClick(new SeckillAdapter.OnSeckillItemClick() {
                @Override
                public void OnItemClick(int position) {
                    Toast.makeText(mContext, "点击"+position, Toast.LENGTH_SHORT).show();
                }
            });
            //计算秒杀倒计时,应为从bean集合里,拿到的时间数据不是int型,所以用Integer进行转换(其逻辑代码就是java基础的内容)
            dt=Integer.valueOf(seckill_info.getEnd_time()) -Integer.valueOf(seckill_info.getStart_time());
            //建立handler实现定时器的效果,循环发送消息,以便能够使时间不断减一
            handler.sendEmptyMessageDelayed(0,1000);
        }


    }

    //主页Recommend的的ViewHolder
    private class RecommendViewHolder extends RecyclerView.ViewHolder {

        private final GridView gv_recommend_home;

        public RecommendViewHolder(Context context, View itemView) {
            super(itemView);
            gv_recommend_home = (GridView) itemView.findViewById(R.id.gv_recommend_home);
        }

        //RecommendViewHolder的设置数据方法
        public void setRecommendData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            RecommendAdapter recommendAdapter=new RecommendAdapter(mContext,recommend_info);
            gv_recommend_home.setAdapter(recommendAdapter);
            recommendAdapter.setOnRecommendItemClick(new RecommendAdapter.OnRecommendItemClick() {
                @Override
                public void OnItemClick(int position) {
                    //传值
                    Intent intent=new Intent(mContext, GoodsShowActivity.class);
                    //实例化GoodsBean对象
                    GoodsBean gb=new GoodsBean();
                    //设置值
                    gb.setName(recommend_info.get(position).getName());
                    gb.setCover_price(recommend_info.get(position).getCover_price());
                    gb.setFigure(recommend_info.get(position).getFigure());
                    intent.putExtra("mgb",gb);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    //主页Hot的的ViewHolder
    private class HotViewHolder extends RecyclerView.ViewHolder {

        private final GridView gv_hot_home;

        public HotViewHolder(Context context, View itemView) {
            super(itemView);
            gv_hot_home = (GridView) itemView.findViewById(R.id.gv_hot_home);
        }

        //HotViewHolder的设置数据方法
        public void setHotData(List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            HotAdapter hotAdapter=new HotAdapter(mContext,hot_info);
            gv_hot_home.setAdapter(hotAdapter);
            hotAdapter.setOnHotItemClick(new HotAdapter.OnHotItemClick() {
                @Override
                public void OnItemClick(int position) {
                    Toast.makeText(mContext, "点击"+position, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}
