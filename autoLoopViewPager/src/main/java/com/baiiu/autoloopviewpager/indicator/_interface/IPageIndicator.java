package com.baiiu.autoloopviewpager.indicator._interface;


import com.baiiu.autoloopviewpager.AutoLoopViewPager;
import com.baiiu.autoloopviewpager.loopvp.LoopViewPager;

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
