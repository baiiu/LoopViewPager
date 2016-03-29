package com.baiiu.loopviewpager.view.looping._interface;

import android.support.v4.view.ViewPager;

/**
 * author: baiiu
 * date: on 16/3/28 10:09
 * description:
 */
public interface ILoopViewPage {

    int getRealCount();

    int getRealCurrentItem();

    public void setOnIndicatorPageChangeListener(ViewPager.OnPageChangeListener listener);

}
