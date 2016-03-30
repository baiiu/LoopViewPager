package com.baiiu.loopviewpager.view.looping.maxvaluevp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

import com.baiiu.loopviewpager.view.autoscroll.AutoScrollViewPager;
import com.baiiu.loopviewpager._interface.ILoopViewPage;

import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/3/28 11:14
 * description:
 */
public class MaxValueViewPager extends AutoScrollViewPager implements ILoopViewPage {

    private MaxAdapterWrapper maxAdapterWrapper;
    private List<OnPageChangeListener> mIndicatorPageChangeListeners;
    private OnPageChangeListener mOnPageChangeListener;

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
    public int getFakeCurrentItem() {
        return super.getCurrentItem();
    }

    @Override
    public void setFakeCurrentItem(int item) {
        item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % getRealCount() + item;
        super.setCurrentItem(item);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        maxAdapterWrapper = new MaxAdapterWrapper(adapter);
        super.setAdapter(maxAdapterWrapper);
        super.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % getRealCount(), false);
    }

    @Override
    public void addOnIndicatorPageChangeListener(OnPageChangeListener listener) {
        if (listener == null) {
            return;
        }

        if (mIndicatorPageChangeListeners == null) {
            mIndicatorPageChangeListeners = new ArrayList<>();
        }

        mIndicatorPageChangeListeners.add(listener);

        removeOnPageChangeListener(mOnPageChangeListener);
        mOnPageChangeListener = createOnPageChangeListener();
        addOnPageChangeListener(mOnPageChangeListener);
    }


    private OnPageChangeListener createOnPageChangeListener() {
        return new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mIndicatorPageChangeListeners != null) {
                    for (OnPageChangeListener listener : mIndicatorPageChangeListeners) {
                        if (listener != null) {
                            listener.onPageScrolled(position % getRealCount(), positionOffset, positionOffsetPixels);
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mIndicatorPageChangeListeners != null) {
                    for (OnPageChangeListener listener : mIndicatorPageChangeListeners) {
                        if (listener != null) {
                            listener.onPageSelected(position % getRealCount());
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mIndicatorPageChangeListeners != null) {
                    for (OnPageChangeListener listener : mIndicatorPageChangeListeners) {
                        if (listener != null) {
                            listener.onPageScrollStateChanged(state);
                        }
                    }
                }
            }
        };
    }


}
