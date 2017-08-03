package com.baiiu.autoloopviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * author: baiiu
 * date: on 16/8/17 15:45
 * description: 宽度固定,根据宽度来确定高度
 */
public class RatioFrameLayout extends FrameLayout {
    /**
     * 默认的宽高比,用于宽高都是wrap_content时,以宽为准,宽高相同
     *
     * height = width * scale
     */
    private static final float DEFAULT_SCALE = 1F;
    private float mScale = DEFAULT_SCALE;

    public RatioFrameLayout(Context context) {
        this(context, null);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioFrameLayout);
        mScale = typedArray.getFloat(R.styleable.RatioFrameLayout_scale, DEFAULT_SCALE);
        typedArray.recycle();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isInEditMode()) return;

        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int size_width = MeasureSpec.getSize(widthMeasureSpec);

        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);

        int width_result;
        int height_result;

        width_result = size_width;

        if (mode_height == MeasureSpec.EXACTLY) {
            height_result = size_height;
        } else {
            height_result = (int) (width_result * mScale + 0.5);
        }


        int measureSpecWidth = MeasureSpec.makeMeasureSpec(width_result, MeasureSpec.EXACTLY);
        int measureSpecHeight = MeasureSpec.makeMeasureSpec(height_result, MeasureSpec.EXACTLY);

        super.onMeasure(measureSpecWidth, measureSpecHeight);
    }
}
