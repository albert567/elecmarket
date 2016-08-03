package com.itheima.elecmarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.holder.MoreHolder;
import com.itheima.elecmarket.manager.ThreadManager;

import java.util.List;

/**
 * Created by zyp on 2016/7/30.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private BaseHolder holder;
    private MoreHolder moreHolder;

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
        //需要加载更多数据
        return mDatas.size() + 1;
    }

    private final int ITEM_VIEW_TYPE = 0;

    private final int MORE_VIEW_TYPE = 1;

    @Override
    public int getItemViewType(int position) {
        if(position == getCount()-1){
            return MORE_VIEW_TYPE;
        }else{
            return getInnerItemViewType(position);
        }
    }

    public int getInnerItemViewType(int position) {
        return ITEM_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount()+1;
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
            if(getItemViewType(i) == MORE_VIEW_TYPE){
                holder = getMoreHolder();
            }else{
                holder = getHolder();
            }
        }
        if(getItemViewType(i) == ITEM_VIEW_TYPE){
            holder.setData(mDatas.get(i));
        }

        return holder.getRootView();
    }

    protected  BaseHolder getMoreHolder(){
        if(moreHolder == null){
            moreHolder = new MoreHolder(this,hasmore());
        }
        return moreHolder;
    }

    protected boolean hasmore() {
        return true;
    }

    public void loadMore() {
        ThreadManager.getLongPool().execute(new Runnable() {
            @Override
            public void run() {
                final List list = onLoadMore();
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if(list == null){
                            getMoreHolder().setData(MoreHolder.ERROR);
                        }else if(list.size() < 20){
                            getMoreHolder().setData(MoreHolder.NO_MORE);
                        }else{
                            getMoreHolder().setData(MoreHolder.HAS_MORE);
                        }
                        mDatas.addAll(list);
                        notifyDataSetChanged();
                    }
                });
            }
        });
    }
    //加载更多
    protected abstract List onLoadMore();

    protected abstract BaseHolder getHolder();
}
