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
import com.itheima.elecmarket.application.utils.LogUtils;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.AppInfo;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.holder.HomeHolder;
import com.itheima.elecmarket.http.HttpHelper;
import com.itheima.elecmarket.protocol.HomeProtocol;
import com.itheima.elecmarket.ui.widget.LoadingPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyp on 2016/7/29.
 */
public class HomeFragment extends BaseFragment{
    private List<AppInfo> mDatas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    protected LoadingPager.LoadResult loadData() {
        /*LogUtils.d("load方法被调用");
        mDatas = new ArrayList<>();
        for(int i = 0;i < 100;i++){
            mDatas.add("我是item" + i);
        }
        LogUtils.d("mDatas的集合长度是：" + mDatas.size());*/
        HomeProtocol protocol = new HomeProtocol();
        mDatas = protocol.load(0);
        return check(mDatas);
    }

    @Override
    protected View createSuccessView() {
        LogUtils.d("createSuccessView被调用");
        LogUtils.d("mDatas == null" + (mDatas == null));
        ListView mListView = new ListView(UIUtils.getContext());
        HomeAdapter<AppInfo> adapter = new HomeAdapter<>(mDatas);
        mListView.setAdapter(adapter);
        return mListView;
    }


    private class HomeAdapter<AppInfo> extends MyBaseAdapter<AppInfo>{
        public HomeAdapter(List<AppInfo> mDatas) {
            super(mDatas);
        }

        @Override
        protected List onLoadMore() {
            HomeProtocol protocol = new HomeProtocol();
            return protocol.load(getData().size());
        }

        @Override
        protected BaseHolder getHolder() {
            return new HomeHolder();
        }
    }

}
