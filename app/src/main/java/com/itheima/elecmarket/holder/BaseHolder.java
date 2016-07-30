package com.itheima.elecmarket.holder;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by zyp on 2016/7/30.
 */
public abstract class BaseHolder<T> {
    private View view;

    private T data;
    public BaseHolder() {
        view = initView();
        view.setTag(this);
    }

    public void setData(T data){
        this.data = data;
        refreshData();
    }

    public T getData(){
        return data;
    }

    public View getRootView(){
        return view;
    }

    protected abstract View initView();
    protected abstract void refreshData();
}
