package com.baiiu.loopviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.baiiu.loopviewpager.adapter.FragmentAdapter;
import com.baiiu.loopviewpager.adapter.ViewAdapter;
import com.baiiu.loopviewpager.data.Data;
import com.baiiu.loopviewpager.indicator.AnimatorCircleIndicator;
import com.baiiu.loopviewpager.indicator.LinePageIndicator;
import com.baiiu.loopviewpager.indicator.SimpleCircleIndicator;
import com.baiiu.loopviewpager.vp.AdvancedLoopViewPager;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.viewPager) AdvancedLoopViewPager viewPager;
  @Bind(R.id.linePageIndicator) LinePageIndicator linePageIndicator;
  @Bind(R.id.indicator) SimpleCircleIndicator simpleCircleIndicator;
  @Bind(R.id.animatorCircleIndicator) AnimatorCircleIndicator animatorCircleIndicator;

  private ViewAdapter viewAdapter;
  private FragmentAdapter fragmentAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ButterKnife.bind(this);

    useView();
    //useFragement();

    //viewPager.setCurrentItem(2);
    //viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    //viewPager.setAutoScrollDurationFactor(5.0);
    viewPager.setInterval(1000);
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
      if (viewAdapter.getCount() == 4) {
        viewAdapter.setList(Data.provideListLocalFive());
      } else {
        viewAdapter.setList(Data.provideListLocalFour());
      }

      //刷新indicator.使用mViewPager.getAdapter().registerDataSetObserver()在某些indicator中不调用...
      linePageIndicator.notifyDataSetChanged();
      simpleCircleIndicator.notifyDataSetChanged();
      animatorCircleIndicator.notifyDataSetChanged();
    }

    // TODO: 16/5/8 fragment notify不起作用
    if (fragmentAdapter != null) {
      if (fragmentAdapter.getCount() == 4) {
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
