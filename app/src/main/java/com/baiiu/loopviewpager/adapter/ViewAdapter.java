package com.baiiu.loopviewpager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.baiiu.loopviewpager.base.BasePagerAdapter;

import java.util.List;

/**
 * author: baiiu
 * date: on 16/3/25 15:00
 * description:
 */
public class ViewAdapter extends BasePagerAdapter<Integer> {

    public ViewAdapter(Context context, List<Integer> list) {
        super(context, list);
    }

    @Override
    public View onCreateView(int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(list.get(position));
        return imageView;
    }

  /**
   * 必须要复写
   */
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
