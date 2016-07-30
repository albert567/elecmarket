package com.itheima.elecmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.elecmarket.adapter.MyBaseAdapter;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.ui.widget.LoadingPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyp on 2016/7/29.
 */
public class HomeFragment extends BaseFragment{
    private List<String> mDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected LoadingPager.LoadResult load() {
        mDatas = new ArrayList<>();
        for(int i = 0;i < 100;i++){
            mDatas.add("我是item" + i);
        }
        return check(mDatas);
    }

    @Override
    protected View createSuccessView() {
        ListView mListView = new ListView(UIUtils.getContext());
        HomeAdapter<String> adapter = new HomeAdapter<>(mDatas);
        mListView.setAdapter(adapter);
        return mListView;
    }


    private class HomeAdapter<String> extends MyBaseAdapter{
        public HomeAdapter(List<String> mDatas) {
            super(mDatas);
        }
        @Override
        protected BaseHolder getHolder() {
            return new ViewHolder();
        }
    }

    private class ViewHolder extends BaseHolder<String>{
        TextView tv;

        @Override
        protected View initView() {
            tv = new TextView(UIUtils.getContext());
            return tv;
        }

        @Override
        protected void refreshData() {
            tv.setText(getData());
        }
    }
}
