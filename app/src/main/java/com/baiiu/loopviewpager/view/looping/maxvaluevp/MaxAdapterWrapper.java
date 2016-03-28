package com.baiiu.loopviewpager.view.looping.maxvaluevp;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: baiiu
 * date: on 16/3/28 11:23
 * description:
 */
public class MaxAdapterWrapper extends PagerAdapter {
    private PagerAdapter mRealAdapter;

    public MaxAdapterWrapper(PagerAdapter adapter) {
        this.mRealAdapter = adapter;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public int getRealCount() {
        return mRealAdapter == null ? 0 : mRealAdapter.getCount();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= getRealCount();
        return mRealAdapter.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mRealAdapter.destroyItem(container, position, object);
    }
}
