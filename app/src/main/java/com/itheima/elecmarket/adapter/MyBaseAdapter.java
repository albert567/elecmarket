package com.itheima.elecmarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.elecmarket.holder.BaseHolder;

import java.util.List;

/**
 * Created by zyp on 2016/7/30.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private BaseHolder holder;

    public MyBaseAdapter(List<T> mDatas){
        setData(mDatas);
    }

    public List<T> mDatas;

    public void setData(List<T> mDatas){
        this.mDatas = mDatas;
    }

    public List<T> getData(){
        return this.mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view != null){
            holder = (BaseHolder) view.getTag();
        }else{
            holder = getHolder();
        }
        holder.setData(mDatas.get(i));
        return holder.getRootView();
    }

    protected abstract BaseHolder getHolder();

}
