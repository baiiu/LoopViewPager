package com.baiiu.loopviewpager.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.baiiu.loopviewpager.vp.IRealAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePagerAdapter<T> extends PagerAdapter implements IRealAdapter {

    protected boolean mCopyTwo = false;
    public List<T> list;
    public int size;
    public Context mContext;

    public BasePagerAdapter(Context context, List<T> list) {
        super();
        this.mContext = context;
        setList(list);
    }

    public void setList(List<T> list) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        mCopyTwo = false;

        if (list.size() == 2) {
            mCopyTwo = true;
            this.list.addAll(new ArrayList<T>(list));
            this.list.addAll(new ArrayList<T>(list));
        } else {
            this.list = list;
        }

        notifyDataSetChanged();
    }

    @Override public int getRealCount() {
        return mCopyTwo ? 2 : getCount();
    }

    @Override public int getCount() {
        return size = list.size();
    }


    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
        View view = onCreateView(position);
        container.addView(view);
        return view;
    }

    /**
     * 初始化
     */
    public abstract View onCreateView(int position);

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
