package com.itheima.elecmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.elecmarket.application.utils.LogUtils;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.ui.widget.LoadingPager;

import java.util.List;

/**
 * Created by zyp on 2016/7/29.
 */
public abstract class BaseFragment extends Fragment {
    private LoadingPager mContentView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d("BaseFragment的onCreateView方法被调用");
        if(mContentView == null){
            mContentView = new LoadingPager(UIUtils.getContext()){

                @Override
                protected View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                protected LoadingPager.LoadResult load() {
                    return BaseFragment.this.loadData();
                }
            };
        }
        return mContentView;
    }

    protected abstract LoadingPager.LoadResult loadData();
    protected abstract View createSuccessView();

    /**
     * 展示具体的页面
     */
    public void show(){
        LogUtils.d("BaseFragment的show方法被调用");
        LogUtils.d("BaseFragment的mContentView==null " +(mContentView == null));
        if(mContentView != null){
            mContentView.show();
        }
    }

    /**
     * 检查服务器数据返回情况
     * @param obj
     * @return
     */
    protected LoadingPager.LoadResult check(Object obj){
        if(obj == null){
            LogUtils.d("list is null");
            return LoadingPager.LoadResult.ERROR;
        }
        if(obj instanceof List){
            List list = (List)obj;
            if(list.size() == 0){
                LogUtils.d("list is empty");
                return LoadingPager.LoadResult.EMPTY;
            }
        }
        LogUtils.d("list is success");
        return LoadingPager.LoadResult.SUCCESS;
    }
}
