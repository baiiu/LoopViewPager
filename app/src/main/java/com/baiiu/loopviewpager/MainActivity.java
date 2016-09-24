package com.baiiu.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.baiiu.autoloopviewpager.AutoLoopViewPager;
import com.baiiu.autoloopviewpager.indicator.AnimatorCircleIndicator;
import com.baiiu.autoloopviewpager.indicator.LinePageIndicator;
import com.baiiu.autoloopviewpager.indicator.SimpleCircleIndicator;
import com.baiiu.loopviewpager.adapter.FragmentAdapter;
import com.baiiu.loopviewpager.adapter.ViewAdapter;
import com.baiiu.loopviewpager.adapter.ViewListAdapter;
import com.baiiu.loopviewpager.data.Data;
import com.baiiu.loopviewpager.transformer.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager) AutoLoopViewPager viewPager;
    @Bind(R.id.linePageIndicator) LinePageIndicator linePageIndicator;
    @Bind(R.id.indicator) SimpleCircleIndicator simpleCircleIndicator;
    @Bind(R.id.animatorCircleIndicator) AnimatorCircleIndicator animatorCircleIndicator;

    private ViewAdapter viewAdapter;
    private ViewListAdapter viewListAdapter;
    private FragmentAdapter fragmentAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        useView();
        //useFixedList();
        //useFragement();

        viewPager.setCurrentItem(2);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAutoScrollDurationFactor(1.0);
        viewPager.setInterval(2000);
        viewPager.startAutoScroll();

        linePageIndicator.setViewPager(viewPager);
        simpleCircleIndicator.setViewPager(viewPager);
        animatorCircleIndicator.setViewPager(viewPager);
    }

    /**
     * 使用View作为Item.
     */
    private void useView() {
        viewAdapter = new ViewAdapter(this, Data.provideListLocalFour());
        viewPager.setAdapter(viewAdapter);
    }

    /**
     * 传入List<ImageView>
     */
    private void useFixedList() {
        viewListAdapter =
                new ViewListAdapter(this, Data.generateImageViews(this, Data.provideListLocalFour()));
        viewPager.setAdapter(viewListAdapter);
    }

    /**
     * 使用Fragment作为Item,adapter必须要继承自FragmentStatePagerAdapter,并且复写getItemPosition方法
     */
    private void useFragement() {
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), Data.provideListLocalFour());
        viewPager.setAdapter(fragmentAdapter);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {

        if (viewAdapter != null) {
            if (viewAdapter.getRealCount() == Data.provideListLocalFour().size()) {
                viewAdapter.setList(Data.provideListLocalFive());
            } else {
                viewAdapter.setList(Data.provideListLocalFour());
            }

            //刷新indicator.使用mViewPager.getAdapter().registerDataSetObserver()在某些indicator中不调用...
            linePageIndicator.notifyDataSetChanged();
            simpleCircleIndicator.notifyDataSetChanged();
            animatorCircleIndicator.notifyDataSetChanged();
        }


        if (viewListAdapter != null) {
            if (viewListAdapter.getRealCount() == Data.provideListLocalFour().size()) {
                viewListAdapter.setList(Data.generateImageViews(this, Data.provideListLocalFive()));
            } else {
                viewListAdapter.setList(Data.generateImageViews(this, Data.provideListLocalFour()));
            }

            //刷新indicator.使用mViewPager.getAdapter().registerDataSetObserver()在某些indicator中不调用...
            linePageIndicator.notifyDataSetChanged();
            simpleCircleIndicator.notifyDataSetChanged();
            animatorCircleIndicator.notifyDataSetChanged();
        }

        if (fragmentAdapter != null) {
            if (fragmentAdapter.getRealCount() == Data.provideListLocalFour().size()) {
                fragmentAdapter.setList(Data.provideListLocalFive());
            } else {
                fragmentAdapter.setList(Data.provideListLocalFour());
            }

            linePageIndicator.notifyDataSetChanged();
            simpleCircleIndicator.notifyDataSetChanged();
            animatorCircleIndicator.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
