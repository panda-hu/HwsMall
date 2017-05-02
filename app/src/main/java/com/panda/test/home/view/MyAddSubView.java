package com.panda.test.home.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panda.test.R;

/**
 * author:huweshuai
 * time:2017/5/2
 */

public class MyAddSubView extends LinearLayout implements View.OnClickListener{

    private final ImageView popu_ib_sub;
    private final ImageView popu_ib_add;
    private final TextView popu_tv_number;

    public MyAddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //把布局文件实例化,并且加载到AddSubView类中.
        View inflate = View.inflate(context, R.layout.popupwindow_add_sub, this);
        //控件初始化
        popu_ib_sub = (ImageView) inflate.findViewById(R.id.popu_ib_sub);
        popu_ib_add = (ImageView) inflate.findViewById(R.id.popu_ib_add);
        popu_tv_number = (TextView) inflate.findViewById(R.id.popu_tv_number);
        //设置点击事件
        popu_ib_add.setOnClickListener(this);
        popu_ib_sub.setOnClickListener(this);

        //获取Value值
        int value = getValue();
        //设置Values值
        setValue(value);
    }

    //当前数量值,默认为1,设置对此值获取,设置的方法
    private int value=1;
    //最小商品数量值
    private int minValue=1;
    //最大商品数量值
    private int maxVaule=5;
    //根据点击事件对TextView做加减的操作
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.popu_ib_sub:
                //减数据
                subNumber();
                break;
            case R.id.popu_ib_add:
                //加数据
                addNumber();
                break;
        }

    }

    private void subNumber() {
        if(value > minValue){
            value--;
        }
        setValue(value);
        //B.当按钮使Value值发送变化时,回调该接口方法.
        if(mOnNumberChangerListener !=null){
            mOnNumberChangerListener.OnNumberChanger(value);
        }
    }

    private void addNumber() {
        if(value < maxVaule){
            value++;
        }
        setValue(value);
        //B.当按钮使Value值发送变化时,回调该接口方法.
        if(mOnNumberChangerListener !=null){
            mOnNumberChangerListener.OnNumberChanger(value);
        }
    }

    //对int商品数据进行获取,设置的方法.(包括商品的最大数,最小数)

    /**
     * 这里获取Value是从UI那里拿到值
     * @return
     */
    public int getValue() {
        //从TextView里拿出其内容,去掉左右两边的空格
        String trim = popu_tv_number.getText().toString().trim();
        if(!TextUtils.isEmpty(trim)){
            //获取出来,因为其值是字符串,所以进行了int型转换
            value = Integer.valueOf(trim);
        }
        return value;
    }

    /**
     * 使设置Value值时,直接对UI进行更新
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
        popu_tv_number.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxVaule() {
        return maxVaule;
    }

    public void setMaxVaule(int maxVaule) {
        this.maxVaule = maxVaule;
    }


    /**
     * B.定义接口,及所要调用的接口方法.当商品数量发送变化时回调该接口.
     */
    public interface OnNumberChangerListener{
        public void OnNumberChanger(int value);
    }
    //B.定义接口对象
    private OnNumberChangerListener mOnNumberChangerListener;
    //B.设置方法接收外界传过来的接口对象方法.
    public void setOnNumberChangerListener(OnNumberChangerListener onNumberChangerListener) {
        mOnNumberChangerListener = onNumberChangerListener;
    }

}
