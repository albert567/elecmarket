package com.itheima.elecmarket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.View;

import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.fragment.AppFragment;
import com.itheima.elecmarket.fragment.BaseFragment;
import com.itheima.elecmarket.fragment.CategoryFragment;
import com.itheima.elecmarket.fragment.FragmentFactory;
import com.itheima.elecmarket.fragment.GameFragment;
import com.itheima.elecmarket.fragment.HomeFragment;
import com.itheima.elecmarket.fragment.HotFragment;
import com.itheima.elecmarket.fragment.RecommendedFragment;
import com.itheima.elecmarket.fragment.SubjectFragment;
import com.itheima.elecmarket.ui.widget.PagerTab;


public class MainActivity extends BaseActivity {

    private PagerTab tabs;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
//        DrawerLayout drawerLayout = new DrawerLayout(UIUtils.getContext());
        //初始化横着滚动的title
        tabs = (PagerTab) findViewById(R.id.tabs);
        //初始化viewpager
        pager = (ViewPager) findViewById(R.id.pager);
        //初始化适配器，传入getFragmentManager()，由于这个方法是高版本的方法，没办法适配
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        //横着滚动的title和下面的fragment绑定到一起
        tabs.setViewPager(pager);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{
        //title的名字
        private String[] tab_names;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            tab_names = UIUtils.getStringArray(R.array.tab_names);
        }
        //如果想实现title的功能，必须实现这个方法
        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }

        @Override
        public Fragment getItem(int position) {

            return FragmentFactory.createFrament(position);
        }

        @Override
        public int getCount() {
            return tab_names.length;
        }
    }

    @Override
    protected void initActionBar() {

    }
}
