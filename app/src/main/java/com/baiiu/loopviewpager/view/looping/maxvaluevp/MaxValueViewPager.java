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

    private MaxAdapterWrapper maxAdapterWrapper;
    OnPageChangeListener mIndicatorPageChangeListener;

    public MaxValueViewPager(Context paramContext) {
        this(paramContext, null);
    }

    public MaxValueViewPager(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public int getRealCount() {
        // 不能 % 0
        return maxAdapterWrapper == null ? 1 : maxAdapterWrapper.getRealCount();
    }

    @Override
    public int getRealCurrentItem() {
        return super.getCurrentItem() % getRealCount();
    }


    @Override
    public void setAdapter(PagerAdapter adapter) {
        maxAdapterWrapper = new MaxAdapterWrapper(adapter);
        super.setAdapter(maxAdapterWrapper);
        setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % getRealCount(), false);
    }

    @Override
    public void setOnIndicatorPageChangeListener(OnPageChangeListener listener) {
        if (listener == null) {
            return;
        }

        mIndicatorPageChangeListener = listener;
        OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mIndicatorPageChangeListener != null) {
                    mIndicatorPageChangeListener.onPageScrolled(position % getRealCount(), positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mIndicatorPageChangeListener != null) {
                    mIndicatorPageChangeListener.onPageSelected(position % getRealCount());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mIndicatorPageChangeListener != null) {
                    mIndicatorPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        };
        addOnPageChangeListener(mOnPageChangeListener);

    }


}
