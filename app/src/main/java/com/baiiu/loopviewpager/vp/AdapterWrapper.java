package com.baiiu.loopviewpager.vp;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: baiiu
 * date: on 16/6/21 11:48
 * description:
 */
public class AdapterWrapper extends PagerAdapter {
    private PagerAdapter mRealAdapter;


    public AdapterWrapper(PagerAdapter adapter) {
        this.mRealAdapter = adapter;
    }

    public PagerAdapter getRealAdapter() {
        return mRealAdapter;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        return mRealAdapter.instantiateItem(container, position % 2);
    }


    @Override public int getCount() {
        return 4;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return mRealAdapter.isViewFromObject(view, object);
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        mRealAdapter.destroyItem(container, position, object);
    }

    @Override public void finishUpdate(ViewGroup container) {
        mRealAdapter.finishUpdate(container);
    }

    @Override public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mRealAdapter.restoreState(bundle, classLoader);
    }

    @Override public Parcelable saveState() {
        return mRealAdapter.saveState();
    }

    @Override public void startUpdate(ViewGroup container) {
        mRealAdapter.startUpdate(container);
    }

    @Override public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mRealAdapter.setPrimaryItem(container, position, object);
    }
}

