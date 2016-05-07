# LoopViewPager
a infinite auto-loop banner with [LoopViewPager](https://github.com/yanzm/LoopViewPager.git)

[中文文档](README-cn.md)


## HighLights:
1. **three layers, each layer has its unique func**
    - the first is LoopViewPager,which provides the infinite func
    - the second is AutoScrollViewPager, which provides auto-start func
    - the third is AdvancedLoopViewPager,which enhances current widget,now supporting `wrap_content` attribute while use `scale` to definate the width/height ratio. 
    
2. **ViewPager.adapter and IPageIndicator all can be notifyDataSetChanged*
    you can use `adapter.notifyDataSetChanged` and `IPageIndicator.notifyDataSetChanged()` to change the data source.
    you should override the `getItemPosition()`,and if you use fragment,you must use `FragmentStatePagerAdapter`. 
    ```
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    ```
    

3. **support setCurrentItem() method** 
    you can use setCurrentItem() method to specified the start position, the indicator changing as well.

4. **implement IPageIndicator,you can have your own indicator**


## Usage:
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
[AnimatorCircleIndicator](https://github.com/ongakuer/CircleIndicator)<br>
图片来自 <a href="http://gank.io/" target="_blank">gank.io</a> 