package com.baiiu.loopviewpager.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

import com.baiiu.loopviewpager.R;
import com.baiiu.loopviewpager.util.LogUtil;
import com.baiiu.loopviewpager.view.looping.LoopingViewPager;

/**
 * auther: baiiu
 * time: 16/3/26 26 21:48
 * description: 对LoopingViewPager进行包装
 * <p/>
 * 1. 添加自定义属性,可以控制宽高
 * 2. to be continued
 */
public class LoopViewPager extends LoopingViewPager {

    /**
     * 默认的宽高比,用于宽高都是wrap_content时
     */
    private static final float DEFAULT_SCALE = 0.5F;

    private float mScale = DEFAULT_SCALE;

    public LoopViewPager(Context context) {
        this(context, null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoopViewPager);
        mScale = typedArray.getFloat(R.styleable.LoopViewPager_scale, DEFAULT_SCALE);
        typedArray.recycle();


        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);

                LogUtil.d("MeasuredWidth: " + getMeasuredWidth() + ", MeasuredHeight: " + getMeasuredHeight());
                return false;
            }
        });

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int width_size = MeasureSpec.getSize(widthMeasureSpec);

        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);


        int width_result = width_size;
        int height_result = height_size;

        if (width_mode == MeasureSpec.EXACTLY) {
            width_result = width_size;

            if (height_mode == MeasureSpec.EXACTLY) {
                height_result = height_size;
            } else {
                height_result = (int) (width_result * mScale + 0.5);
            }
        } else {
            //宽度也包裹内容
            width_result = width_size;

            if (height_mode == MeasureSpec.EXACTLY) {
                height_result = height_size;
            } else {
                height_result = (int) (width_result * mScale + 0.5);
            }
        }

        int measureSpecWidth = MeasureSpec.makeMeasureSpec(width_result, MeasureSpec.EXACTLY);
        int measureSpecHeight = MeasureSpec.makeMeasureSpec(height_result, MeasureSpec.EXACTLY);

        super.onMeasure(measureSpecWidth, measureSpecHeight);
    }
}
