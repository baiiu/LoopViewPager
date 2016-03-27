package com.baiiu.loopviewpager.indicator;

import android.support.v4.view.ViewPager;

/**
 * auther: baiiu
 * time: 16/3/27 27 08:36
 * description: indicator接口,可实现该接口写自己的indicator
 */
public interface IPageIndicator {

    void setViewPager(ViewPager view);

    void setViewPager(ViewPager view, int initialPosition);

    void setCurrentItem(int item);

    void notifyDataSetChanged();
}
