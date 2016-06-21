package com.baiiu.loopviewpager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.baiiu.loopviewpager.base.BasePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * author: baiiu
 * date: on 16/3/25 15:00
 * description:
 */
public class ViewListAdapter extends BasePagerAdapter<ImageView> {

    public ViewListAdapter(Context context, List<ImageView> list) {
        super(context, list);
    }

    @Override public void setList(List<ImageView> list) {
        if (list == null) {
            list = new ArrayList<ImageView>();
        }

        mCopyTwo = false;

        if (list.size() == 2) {
            mCopyTwo = true;

            ImageView imageView1 = list.get(0);
            ImageView imageView3 = new ImageView(mContext);
            copyImageView(imageView1,imageView3);

            ImageView imageView2 = list.get(1);
            ImageView imageView4 = new ImageView(mContext);
            copyImageView(imageView2,imageView4);

            list.add(imageView3);
            list.add(imageView4);
        }

        this.list = list;
        notifyDataSetChanged();
    }

    private void copyImageView(ImageView source,ImageView target){
        target.setImageDrawable(source.getDrawable());
    }

    @Override public View onCreateView(int position) {
        return list.get(position);
    }

    /**
     * 必须要复写
     */
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
