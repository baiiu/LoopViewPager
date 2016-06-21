package com.baiiu.loopviewpager.indicator._interface;

import com.baiiu.loopviewpager.vp.AutoLoopViewPager;
import com.baiiu.loopviewpager.vp.loopvp.LoopViewPager;

/**
 * auther: baiiu
 * time: 16/3/27 27 08:36
 * description: indicator接口,可实现该接口写自己的indicator
 */
public interface IPageIndicator extends LoopViewPager.OnPageChangeListener {

    void setViewPager(AutoLoopViewPager viewPager);

    void setCurrentItem(int item);

    void notifyDataSetChanged();
}
