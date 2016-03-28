package com.baiiu.loopviewpager.view.looping.maxvaluevp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

import com.baiiu.loopviewpager.view.autoscroll.AutoScrollViewPager;
import com.baiiu.loopviewpager.view.looping._interface.ILoopViewPage;

/**
 * author: baiiu
 * date: on 16/3/28 11:14
 * description:
 */
public class MaxValueViewPager extends AutoScrollViewPager implements ILoopViewPage {

    public MaxValueViewPager(Context paramContext) {
        this(paramContext, null);
    }

    public MaxValueViewPager(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public int getRealCount() {
        return 0;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        MaxAdapterWrapper maxAdapterWrapper = new MaxAdapterWrapper(adapter);
        super.setAdapter(adapter);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }


}
