# LoopViewPager
无限循环轮播banner. [blog链接](http://blog.csdn.net/u014099894/article/details/50987819)

## 特点:
1. 使用三层继承关系,分工明确.
    - 顶层使用Trina的AutoScrollViewPager,作为启动器.
    - 第二层用于添加无限Loop功能. 现在提供了两种, LoopingViewPager和MaxValueViewPager.
    - 第三次LoopViewPager,用于功能增强,现在提供了自定义属性scale,用于设置宽高比
    
2. 提供了简单的SimpleCircleIndicator,后面会继续增强.

3. to be continued...

## 使用:
```java
    viewPager.setAdapter(new ViewAdapter(this, Data.provideListLocal()));
    viewPager.setPageTransformer(true, new DepthPageTransformer());
    viewPager.setAutoScrollDurationFactor(5.0);
//  viewPager.setInterval(2000);
    viewPager.startAutoScroll();
```

## Thanks To
[Trinea的android-auto-scroll-view-pager](https://github.com/Trinea/android-auto-scroll-view-pager)
[LoopingViewPager](https://github.com/imbryk/LoopingViewPager)
[FlycoBanner](https://github.com/H07000223/FlycoBanner_Master)