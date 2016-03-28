package com.baiiu.loopviewpager.view.looping.maxvaluevp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
        addOnPageChangeListener(mOnPageChangeListener);
    }

    @Override
    public int getRealCount() {
        return maxAdapterWrapper.getRealCount();
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        maxAdapterWrapper = new MaxAdapterWrapper(adapter);
        super.setAdapter(maxAdapterWrapper);
    }

    @Override
    public void setCurrentItem(int item) {
        if (item == 0) {
            item = (Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % getRealCount());
        }
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (item == 0) {
            item = (Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % getRealCount());
        }
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setOnIndicatorPageChangeListener(OnPageChangeListener listener) {
        mIndicatorPageChangeListener = listener;
    }


    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
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

}
