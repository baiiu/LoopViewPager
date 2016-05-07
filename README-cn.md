# LoopViewPager
无限循环轮播banner. [blog链接](http://blog.csdn.net/u014099894/article/details/50987819)

## 特点:
1. **使用三层继承关系,分工明确.**
    - 顶层使用LoopView,该控件继承ViewGroup,是一个可以无限轮播的ViewPager
    - 第二层使用Trina的AutoScrollViewPager,作为启动器.
    - 第三层AdvancedLoopViewPager,用于功能增强,现在提供了自定义属性scale,用于设置宽高比
    
2. **ViewPager的Adapter 和 IPageIndicator 都支持notifyDateSetChanged()方法.**
   可以通过该方法改变数据源.
   注意,在adapter中必须要复写`getItemPosition`方法,使用Fragment的话必须使用FragmentStatePagerAdapter
   ```
     public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
   ```

3. **支持setCurrentItem()方法.**
   通过viewPager.setCurrentItem(2)设置.
   并且在viewPager中设置后,indicator中不用设置,直接跟着变化.

4. 提供了IPageIndicator接口,可以实现自己的Indicator. 写法都很固定,无论是继承View还是ViewGroup.


## 使用:
```java
    viewPager.setAdapter(new ViewAdapter(this, Data.provideListLocal()));
    viewPager.setPageTransformer(true, new DepthPageTransformer());
    viewPager.setAutoScrollDurationFactor(5.0);
    viewPager.startAutoScroll();
    
    //设置indicator
    linePageIndicator.setViewPager(viewPager);
    simpleCircleIndicator.setViewPager(viewPager);
    animatorCircleIndicator.setViewPager(viewPager);
```

## ScreenShots
![LoopViewPager](images/loopvp.gif "loopvp Example")


## Thanks To
[Trinea的android-auto-scroll-view-pager](https://github.com/Trinea/android-auto-scroll-view-pager)<br>
[LoopViewPager](https://github.com/yanzm/LoopViewPager.git)<br />
[FlycoBanner](https://github.com/H07000223/FlycoBanner_Master)<br>
[AnimatorCircleIndicator](https://github.com/ongakuer/CircleIndicator)<br>
图片来自 <a href="http://gank.io/" target="_blank">gank.io</a> 