package com.baiiu.loopviewpager.view;

import android.content.Context;
import android.util.AttributeSet;

import com.baiiu.loopviewpager.view.looping.LoopingViewPager;

/**
 * auther: baiiu
 * time: 16/3/26 26 21:48
 * description: 对LoopingViewPager进行包装
 * <p>
 * 1. 添加自定义属性,可以控制宽高
 * 2. to be continued
 */
public class LoopViewPager extends LoopingViewPager {

    public LoopViewPager(Context context) {
        super(context);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



}
