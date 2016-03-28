package com.baiiu.loopviewpager.view.looping._interface;

import android.support.v4.view.PagerAdapter;

/**
 * author: baiiu
 * date: on 16/3/28 10:09
 * description:
 */
public interface ILoopViewPage {

    void setAdapter(PagerAdapter adapter);

    int getRealCount();

    void setCurrentItem(int item);

    int getCurrentItem();

    void setCurrentItem(int item, boolean smoothScroll);

    PagerAdapter getAdapter();

}
