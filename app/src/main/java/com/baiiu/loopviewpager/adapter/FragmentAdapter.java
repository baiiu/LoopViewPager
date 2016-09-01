package com.baiiu.loopviewpager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.baiiu.autoloopviewpager.IRealAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/3/31 15:40
 * description: 必须用fragmentStatePagerAdapter.否则不会切换.
 * <p/>
 * 原因:
 * FragmentPagerAdapter源码:
 * 它使用了mCurTransaction缓存fragment,fragment命名以viewPager的id和position命名.造成fragment复用.
 * notifyDataSetChanged()方法不会刷新数据.
 * <p/>
 * 具体请看:
 * <a href="http://www.trinea.cn/android/android-source-code-analysis/multi-viewpager-to-fragment-not-init/">Trina的文章</>
 */
public class FragmentAdapter extends FragmentStatePagerAdapter implements IRealAdapter {

    private List<Integer> list;
    private boolean mCopyTwo = false;

    public FragmentAdapter(FragmentManager fm, List<Integer> list) {
        super(fm);
        setList(list);
    }

    public void setList(List<Integer> list) {
        if (list == null) {
            list = new ArrayList<Integer>();
        }
        mCopyTwo = false;

        if (list.size() == 2) {
            mCopyTwo = true;
            this.list.addAll(new ArrayList<>(list));
            this.list.addAll(new ArrayList<>(list));
        }


        notifyDataSetChanged();
    }

    @Override public Fragment getItem(int position) {
        return ImageFragment.instance(list.get(position));
    }

    @Override public int getCount() {
        return list.size();
    }

    /**
     * 必须复写getItemPosition方法
     */
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override public int getRealCount() {
        return mCopyTwo ? 2 : getCount();
    }
}
