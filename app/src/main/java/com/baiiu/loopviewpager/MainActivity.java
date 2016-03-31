package com.baiiu.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.baiiu.loopviewpager.adapter.FragmentAdapter;
import com.baiiu.loopviewpager.adapter.ViewAdapter;
import com.baiiu.loopviewpager.data.Data;
import com.baiiu.loopviewpager.indicator.AnimatorCircleIndicator;
import com.baiiu.loopviewpager.indicator.BetterCircleIndicator;
import com.baiiu.loopviewpager.indicator.LinePageIndicator;
import com.baiiu.loopviewpager.indicator.SimpleCircleIndicator;
import com.baiiu.loopviewpager.view.LoopViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    LoopViewPager viewPager;

    @Bind(R.id.linePageIndicator)
    LinePageIndicator linePageIndicator;

    @Bind(R.id.indicator)
    SimpleCircleIndicator simpleCircleIndicator;

    @Bind(R.id.animatorCircleIndicator)
    AnimatorCircleIndicator animatorCircleIndicator;

    @Bind(R.id.betterIndicator)
    BetterCircleIndicator betterIndicator;

    private ViewAdapter viewAdapter;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        useView();
//        useFragement();

//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        viewPager.setAutoScrollDurationFactor(5.0);
        viewPager.setInterval(1000);
        viewPager.startAutoScroll();

//        viewPager.setFakeCurrentItem(2);

        linePageIndicator.setViewPager(viewPager);
        simpleCircleIndicator.setViewPager(viewPager);
        animatorCircleIndicator.setViewPager(viewPager);
        betterIndicator.setViewPager(viewPager);
        betterIndicator.setIndicatorMode(BetterCircleIndicator.Mode.OUTSIDE);
    }

    /**
     * 使用View作为Item.
     */
    private void useView() {
        viewAdapter = new ViewAdapter(this, Data.provideListLocalFour());
        viewPager.setAdapter(viewAdapter);
    }

    /**
     * 使用Fragment作为Item,不开启boundaryCaching,否则会在notifyDataSetChanged时因为复用造成空白问题
     */
    private void useFragement() {
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), Data.provideListLocalFour());
        viewPager.setAdapter(fragmentAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (viewAdapter != null) {
            if (viewAdapter.getCount() == 4) {
                viewAdapter.setList(Data.provideListLocalFive());
            } else {
                viewAdapter.setList(Data.provideListLocalFour());
            }

            //因为LoopingViewPager本身,直接重新设置adapter.
            //使用viewPager.getAdapter().notifyDataSetChanged(),使用wrapperAdapter刷新会出现意想不到的问题
            viewPager.setAdapter(viewAdapter);

            //刷新indicator.使用mViewPager.getAdapter().registerDataSetObserver()在某些indicator中不调用...
            linePageIndicator.notifyDataSetChanged();
            simpleCircleIndicator.notifyDataSetChanged();
            animatorCircleIndicator.notifyDataSetChanged();
            betterIndicator.notifyDataSetChanged();

        }

        if (fragmentAdapter != null) {
            if (fragmentAdapter.getCount() == 4) {
                fragmentAdapter.setList(Data.provideListLocalFive());
            } else {
                fragmentAdapter.setList(Data.provideListLocalFour());
            }

            viewPager.setAdapter(fragmentAdapter);
            linePageIndicator.notifyDataSetChanged();
            simpleCircleIndicator.notifyDataSetChanged();
            animatorCircleIndicator.notifyDataSetChanged();
            betterIndicator.notifyDataSetChanged();
        }


        return super.onOptionsItemSelected(item);
    }
}
