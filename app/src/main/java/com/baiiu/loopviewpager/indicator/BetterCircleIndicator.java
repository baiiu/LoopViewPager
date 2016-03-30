package com.baiiu.loopviewpager.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.baiiu.loopviewpager.R;
import com.baiiu.loopviewpager._interface.ILoopViewPage;
import com.baiiu.loopviewpager._interface.IPageIndicator;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xiayong on 2015/9/29.
 * <a href="https://github.com/THEONE10211024/CircleIndicator">CircleIndicator</>
 */
public class BetterCircleIndicator extends View implements IPageIndicator {
    private ViewPager viewPager;
    private List<ShapeHolder> tabItems;
    private ShapeHolder movingItem;

    //config list
    private int mCurItemPosition;
    private float mCurItemPositionOffset;
    private float mIndicatorRadius;
    private float mIndicatorMargin;
    private int mIndicatorBackground;
    private int mIndicatorSelectedBackground;
    private Gravity mIndicatorLayoutGravity;
    private Mode mIndicatorMode;

    //default value
    private final int DEFAULT_INDICATOR_RADIUS = 10;
    private final int DEFAULT_INDICATOR_MARGIN = 40;
    private final int DEFAULT_INDICATOR_BACKGROUND = Color.BLUE;
    private final int DEFAULT_INDICATOR_SELECTED_BACKGROUND = Color.RED;
    private final int DEFAULT_INDICATOR_LAYOUT_GRAVITY = Gravity.CENTER.ordinal();
    private final int DEFAULT_INDICATOR_MODE = Mode.SOLO.ordinal();


    public enum Gravity {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum Mode {
        INSIDE,
        OUTSIDE,
        SOLO
    }

    public BetterCircleIndicator(Context context) {
        super(context);
        init(context, null);
    }

    public BetterCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BetterCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        tabItems = new ArrayList<>();
        handleTypedArray(context, attrs);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs == null)
            return;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnotherCircleIndicator);
        mIndicatorRadius = typedArray.getDimensionPixelSize(R.styleable.AnotherCircleIndicator_another_ci_radius, DEFAULT_INDICATOR_RADIUS);
        mIndicatorMargin = typedArray.getDimensionPixelSize(R.styleable.AnotherCircleIndicator_another_ci_margin, DEFAULT_INDICATOR_MARGIN);
        mIndicatorBackground = typedArray.getColor(R.styleable.AnotherCircleIndicator_another_ci_background, DEFAULT_INDICATOR_BACKGROUND);
        mIndicatorSelectedBackground = typedArray.getColor(R.styleable.AnotherCircleIndicator_another_ci_selected_background, DEFAULT_INDICATOR_SELECTED_BACKGROUND);
        int gravity = typedArray.getInt(R.styleable.AnotherCircleIndicator_another_ci_gravity, DEFAULT_INDICATOR_LAYOUT_GRAVITY);
        mIndicatorLayoutGravity = Gravity.values()[gravity];
        int mode = typedArray.getInt(R.styleable.AnotherCircleIndicator_another_ci_mode, DEFAULT_INDICATOR_MODE);
        mIndicatorMode = Mode.values()[mode];
        typedArray.recycle();
    }

    @Override
    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null || viewPager.getAdapter() == null) {
            throw new IllegalStateException("you must initial the viewpager with adapter");
        }

        int initialPosition = 0;

        if (viewPager instanceof ILoopViewPage) {
            ILoopViewPage loopViewPage = (ILoopViewPage) viewPager;
            loopViewPage.addOnIndicatorPageChangeListener(this);
            initialPosition = loopViewPage.getRealCurrentItem();
        } else {
            viewPager.removeOnPageChangeListener(this);
            viewPager.addOnPageChangeListener(this);
        }

        this.viewPager = viewPager;
        createTabItems();
        createMovingItem();

        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        onPageSelected(item);
    }

    @Override
    public void notifyDataSetChanged() {

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mIndicatorMode != Mode.SOLO) {
            trigger(position, positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (mIndicatorMode == Mode.SOLO) {
            trigger(position, 0);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * trigger to redraw the indicator when the ViewPager's selected item changed!
     *
     * @param position
     * @param positionOffset
     */
    private void trigger(int position, float positionOffset) {
        mCurItemPosition = position;
        mCurItemPositionOffset = positionOffset;
        requestLayout();
        invalidate();
    }

    private void createTabItems() {
        int count;

        if (viewPager instanceof ILoopViewPage) {
            count = ((ILoopViewPage) viewPager).getRealCount();
        } else {
            count = viewPager.getAdapter().getCount();
        }


        for (int i = 0; i < count; i++) {
            OvalShape circle = new OvalShape();
            ShapeDrawable drawable = new ShapeDrawable(circle);
            ShapeHolder shapeHolder = new ShapeHolder(drawable);
            Paint paint = drawable.getPaint();
            paint.setColor(mIndicatorBackground);
            paint.setAntiAlias(true);
            shapeHolder.setPaint(paint);
            tabItems.add(shapeHolder);
        }
    }

    private void createMovingItem() {
        OvalShape circle = new OvalShape();
        ShapeDrawable drawable = new ShapeDrawable(circle);
        movingItem = new ShapeHolder(drawable);
        Paint paint = drawable.getPaint();
        paint.setColor(mIndicatorSelectedBackground);
        paint.setAntiAlias(true);

        switch (mIndicatorMode) {
            case INSIDE:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                break;
            case OUTSIDE:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
                break;
            case SOLO:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                break;
        }

        movingItem.setPaint(paint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int width = getWidth();
        final int height = getHeight();
        layoutTabItems(width, height);
        layoutMovingItem(mCurItemPosition, mCurItemPositionOffset);
    }

    private void layoutTabItems(final int containerWidth, final int containerHeight) {
        if (tabItems == null) {
            throw new IllegalStateException("forget to create tabItems?");
        }
        final float yCoordinate = containerHeight * 0.5f;
        final float startPosition = startDrawPosition(containerWidth);
        for (int i = 0; i < tabItems.size(); i++) {
            ShapeHolder item = tabItems.get(i);
            item.resizeShape(2 * mIndicatorRadius, 2 * mIndicatorRadius);
            item.setY(yCoordinate - mIndicatorRadius);
            float x = startPosition + (mIndicatorMargin + mIndicatorRadius * 2) * i;
            item.setX(x);
        }

    }

    private float startDrawPosition(final int containerWidth) {
        if (mIndicatorLayoutGravity == Gravity.LEFT)
            return 0;
        float tabItemsLength = tabItems.size() * (2 * mIndicatorRadius + mIndicatorMargin) - mIndicatorMargin;
        if (containerWidth < tabItemsLength) {
            return 0;
        }
        if (mIndicatorLayoutGravity == Gravity.CENTER) {
            return (containerWidth - tabItemsLength) / 2;
        }
        return containerWidth - tabItemsLength;
    }

    private void layoutMovingItem(final int position, final float positionOffset) {
        if (movingItem == null) {
            return;
        }
        ShapeHolder item = tabItems.get(position);
        movingItem.resizeShape(item.getWidth(), item.getHeight());
        float x = item.getX() + (mIndicatorMargin + mIndicatorRadius * 2) * positionOffset;
        movingItem.setX(x);
        movingItem.setY(item.getY());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null,
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        for (ShapeHolder item : tabItems) {
            canvas.save();
            canvas.translate(item.getX(), item.getY());
            item.getShape().draw(canvas);
            canvas.restore();
        }

        if (movingItem != null) {
            canvas.save();
            canvas.translate(movingItem.getX(), movingItem.getY());
            movingItem.getShape().draw(canvas);
            canvas.restore();
        }
        canvas.restoreToCount(sc);
    }

}
