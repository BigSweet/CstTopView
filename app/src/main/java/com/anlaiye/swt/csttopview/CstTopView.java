package com.anlaiye.swt.csttopview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


/*首先定义好我们需要的控件，一般一个ToolBar分为3个部分，左边中间和右边，我们用3个TextView来表示，同时把属性也先初始化好

* */
public class CstTopView extends RelativeLayout {
    //3个对应的控件
    private TextView LeftTv, RightTv, TitleTv;

    //控件相对应的一些属性
    private float LeftSize, RightSize, TltleSize;
    private String LeftText, RightText, TtleText;
    private Drawable TitlebgColor, LeftTextbgColor, RightTexbgtColor;
    private ToolListener listener;
    private Drawable LeftBg, RightBg, TitleBg;

    //控件的位置属性
    private LayoutParams LeftParams, RightParams, TitleParams;

    public CstTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //使用TypedArray来保存我们在atts中设置的属性，通过context获取我们相对应的xml文件的属性数组
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CstTopView);
        //在TypedArray中，属性是通过键值对的形式存在的，所以我们在取相对应的属性的时候，通过我们在xml中设置的属性类型来获取
        //第一个属性是我们在xml中设置的，第二个是默认属性，就是在第一个属性为空的时候，默认展示出来的，这里我设置为0
        LeftSize = ta.getDimension(R.styleable.CstTopView_LeftTextSize, 0);
        RightSize = ta.getDimension(R.styleable.CstTopView_RightTextSize, 0);
        TltleSize = ta.getDimension(R.styleable.CstTopView_TitleSize, 0);
        LeftText = ta.getString(R.styleable.CstTopView_LeftText);
        RightText = ta.getString(R.styleable.CstTopView_RightText);
        TtleText = ta.getString(R.styleable.CstTopView_TitleText);
        TitlebgColor = ta.getDrawable(R.styleable.CstTopView_TitleColor);
        LeftTextbgColor = ta.getDrawable(R.styleable.CstTopView_RightTextColor);
        RightTexbgtColor = ta.getDrawable(R.styleable.CstTopView_RightTextColor);
        LeftBg = ta.getDrawable(R.styleable.CstTopView_leftBG);
        RightBg = ta.getDrawable(R.styleable.CstTopView_rightBg);
        TitleBg = ta.getDrawable(R.styleable.CstTopView_titleBG);
        //使用完记得回收，放置缓存引起的bug
        ta.recycle();
        //获得这些属性之后,把相对应的控件New出来并设置上这些属性;
        LeftTv = new TextView(context);
        RightTv = new TextView(context);
        TitleTv = new TextView(context);

        LeftTv.setText(LeftText);
        LeftTv.setTextSize(LeftSize);
        LeftTv.setBackground(LeftTextbgColor);
        LeftTv.setBackground(LeftBg);

        RightTv.setText(RightText);
        RightTv.setTextSize(RightSize);
        RightTv.setBackground(RightTexbgtColor);
        RightTv.setBackground(RightBg);

        TitleTv.setText(TtleText);
        TitleTv.setTextSize(TltleSize);
        TitleTv.setBackground(TitlebgColor);
        TitleTv.setGravity(Gravity.CENTER);
        TitleTv.setBackground(TitleBg);

        //给我们的topview设置一个背景，用来区分
        setBackgroundResource(R.color.yellow);

        //设置控件的位置信息，并添加到viewgroup中
        LeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        LeftParams.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);
        RightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        RightParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        TitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TitleParams.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);

        addView(LeftTv, LeftParams);
        addView(RightTv, RightParams);
        addView(TitleTv, TitleParams);
        //通过内部的对象对象，加监听
        LeftTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.LeftClick();
            }
        });

        RightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.RightClick();
            }
        });
    }

    //然后我们给这些控件加上监听首先我们定义一个接口,实现俩个方法，左边的监听和右边的监听
    public interface ToolListener {
        void LeftClick();

        void RightClick();
    }

    //然后对外部暴露一个方法，通过接口回调来调用,这样就连接起来了
    public void SetTopListener(ToolListener listener) {
        this.listener = listener;
    }


    


}
