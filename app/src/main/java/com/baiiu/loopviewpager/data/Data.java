package com.baiiu.loopviewpager.data;

import android.content.Context;
import android.widget.ImageView;
import com.baiiu.loopviewpager.R;
import java.util.ArrayList;
import java.util.List;

/**
 * auther: baiiu
 * time: 16/3/26 26 17:11
 * description:
 */
public class Data {

    public static List<Integer> provideListLocalFour() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.gank610dc034jw1f27tuwswd3j20hs0qoq6q);
        list.add(R.drawable.gank7a8aed7bgw1etlw75so1hj20qo0hsgpk);
        list.add(R.drawable.gank7a8aed7bjw1f25gtggxqjj20f00b9tb5);
        list.add(R.drawable.gank7a8aed7bjw1f27uhoko12j20ez0miq4p);
        return list;
    }

    public static List<ImageView> generateImageViews(Context context, List<Integer> list) {

        List<ImageView> imageViews = new ArrayList<>();
        int size = list.size();

        for (int i = 0; i < size; ++i) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(list.get(i));
            imageViews.add(imageView);
        }

        return imageViews;
    }

    public static List<Integer> provideListLocalFive() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.a);
        list.add(R.drawable.b);
        list.add(R.drawable.c);
        list.add(R.drawable.d);
        list.add(R.drawable.e);
        return list;
    }

    public static List<String> provideListRemote() {
        List<String> list = new ArrayList<>();
        list.add("http://ww1.sinaimg.cn/large/7a8aed7bjw1f20ruz456sj20go0p0wi3.jpg");
        list.add("http://ww3.sinaimg.cn/large/7a8aed7bgw1etlw75so1hj20qo0hsgpk.jpg");
        list.add("http://ww2.sinaimg.cn/large/7a8aed7bjw1f25gtggxqjj20f00b9tb5.jpg");
        list.add("http://ww1.sinaimg.cn/large/7a8aed7bjw1f27uhoko12j20ez0miq4p.jpg");
        list.add("http://ww2.sinaimg.cn/large/610dc034jw1f27tuwswd3j20hs0qoq6q.jpg");

        return list;
    }
}
