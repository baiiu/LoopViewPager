# LoopViewPager
无限循环轮播banner. [blog链接](http://blog.csdn.net/u014099894/article/details/50987819)

## 特点:
1. **使用三层继承关系,分工明确.**
    - 顶层使用Trina的AutoScrollViewPager,作为启动器.
    - 第二层用于添加无限Loop功能.
    - 第三次LoopViewPager,用于功能增强,现在提供了自定义属性scale,用于设置宽高比
    
2. **LoopViewPager和IPageIndicator都支持notifyDateSetChanged()方法.**
   可以通过该方法改变数据源.

3. **支持setCurrentItem()方法.**
   通过viewPager.setFakeCurrentItem(2)设置.
   并且在viewPager中设置后,indicator中不用设置,直接跟着变化.

4. 提供了ILoopViewPager接口,可以实现自己的Loop方式.
   提供了IPageIndicator接口,可以实现自己的Indicator. 写法都很固定,无论是继承View还是ViewGroup.


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
    betterIndicator.setViewPager(viewPager);
```

## ScreenShots
![LoopViewPager](images/loopvp.gif "loopvp Example")



## Thanks To
[Trinea的android-auto-scroll-view-pager](https://github.com/Trinea/android-auto-scroll-view-pager)<br>
[LoopingViewPager](https://github.com/imbryk/LoopingViewPager)<br>
[FlycoBanner](https://github.com/H07000223/FlycoBanner_Master)