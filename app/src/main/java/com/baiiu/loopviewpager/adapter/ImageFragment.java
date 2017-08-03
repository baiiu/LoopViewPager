package com.baiiu.loopviewpager.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.baiiu.loopviewpager.R;

/**
 * author: baiiu
 * date: on 16/3/31 15:41
 * description:
 */
public class ImageFragment extends Fragment {
    private static final String RES_ID = "resId";
    private static final String POSITION = "position";
    private int anInt;

    public static ImageFragment instance(int resId) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(RES_ID, resId);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            anInt = arguments.getInt(RES_ID, R.mipmap.ic_launcher);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //TextView textView = new TextView(container.getContext());
        //textView.setText(""+anInt);
        //textView.setTextSize(22);
        //textView.setTextColor(Color.WHITE);
        //textView.setGravity(Gravity.CENTER);
        //return textView;
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(anInt);
        return imageView;
    }
}
