package com.baiiu.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.baiiu.loopviewpager.adapter.ViewAdapter;
import com.baiiu.loopviewpager.data.Data;
import com.baiiu.loopviewpager.indicator.SimpleCircleIndicator;
import com.baiiu.loopviewpager.view.LoopViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.viewPager)
    LoopViewPager viewPager;

    @Bind(R.id.indicator)
    SimpleCircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        viewPager.setAdapter(new ViewAdapter(this, Data.provideListLocal()));
        viewPager.startAutoScroll();

//        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                viewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                LogUtil.d("MeasuredWidth: " + viewPager.getMeasuredWidth() + ", MeasuredHeight: " + viewPager.getMeasuredHeight() + ", getWidth:" + viewPager.getWidth() + ", getHeight: " + viewPager.getHeight());
//            }
//        });

        indicator.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
