package com.itheima.elecmarket.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * 创建fragment工厂
 * Created by zyp on 2016/7/29.
 */
public class FragmentFactory {
    //首页
    private static final int TAB_HOME = 0;
    //应用
    private static final int TAB_APP = 1;
    //游戏
    private static final int TAB_GAME = 2;
    //专题
    private static final int TAB_SUBJECT = 3;
    //推荐
    private static final int TAB_RECOMMENDED = 4;
    //分类
    private static final int TAB_CATEGORY = 5;
    //排行
    private static final int TAB_HOT = 6;
    //设置缓存fragment的数据
    private static HashMap<Integer,BaseFragment> mFragments = new HashMap<Integer,BaseFragment>();

    public static Fragment createFrament(int position){
        //从缓存里取出fragment
        BaseFragment fragment = mFragments.get(position);
        if(fragment == null){
            switch (position){
                case TAB_HOME://首页
                    fragment = new HomeFragment();
                    break;
                case TAB_APP://应用
                    fragment = new AppFragment();
                    break;
                case TAB_GAME://游戏
                    fragment = new GameFragment();
                    break;
                case TAB_SUBJECT://专题
                    fragment = new SubjectFragment();
                    break;
                case TAB_RECOMMENDED://推荐
                    fragment = new RecommendedFragment();
                    break;
                case TAB_CATEGORY://分类
                    fragment = new CategoryFragment();
                    break;
                case TAB_HOT://排行
                    fragment = new HotFragment();
                    break;
            }
            //把fragment放到缓存里
            mFragments.put(position,fragment);
        }

        return fragment;
    }

}
