package com.itheima.elecmarket.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.itheima.elecmarket.R;
import com.itheima.elecmarket.application.utils.LogUtils;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.manager.ThreadManager;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zyp on 2016/7/30.
 */
public abstract class LoadingPager extends FrameLayout {
    //加载默认的状态
    private static final int STATE_UNLOADING = 1;
    //Loading状态
    private static final int STATE_LOADING = 2;
    //加载失败状态
    private static final int STATE_ERROR = 3;
    //加载空的状态
    private static final int STATE_EMPTY = 4;
    //加载成功的状态
    private static final int STATE_SUCCESS = 5;

    private int mState;//默认的状态

    private View loadingView;//正在加载页面
    private View errorView;//加载失败页面
    private View emptyView;//加载空页面
    private View successView;//加载成功页面

    public LoadingPager(Context context) {
        this(context,null,0);
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化默认状态
        mState = STATE_UNLOADING;
        loadingView  = createLoadingView();
        if(null != loadingView){
            addView(loadingView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView();
        if(null != emptyView){
            addView(emptyView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();
        if(null != errorView){
            addView(errorView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        }
        
        showSafePagerView();
    }

    protected abstract View createSuccessView();
    protected  abstract LoadResult load();

    private void showSafePagerView() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPagerView();
            }
        });
    }

    private void showPagerView() {
        LogUtils.d("showPagerView方法被调用");
        if(null != loadingView){
            loadingView.setVisibility(mState == STATE_UNLOADING || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }

        if(null != errorView){
            errorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }

        if(null != emptyView){
            emptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }

        if(null == successView && mState == STATE_SUCCESS){
            successView = createSuccessView();
            addView(successView,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        }

        if (null != successView) {
            successView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE
                    : View.INVISIBLE);
        }

    }

    public void show(){
        LogUtils.d("LoadingPager的show方法被调用");
        if(mState == STATE_ERROR || mState == STATE_EMPTY){
            mState = STATE_UNLOADING;
        }
        if(mState == STATE_UNLOADING){
            mState = STATE_LOADING;

            ThreadManager manager = new ThreadManager();
            TaskRunnable runnable = new TaskRunnable();
            manager.getLongPool().execute(runnable);
            /*ExecutorService executorService = Executors.newFixedThreadPool(5);
            TaskRunnable runnable = new TaskRunnable();
            executorService.execute(runnable);*/
        }
    }

    private class TaskRunnable implements  Runnable{
        @Override
        public void run() {
            final LoadResult result = load();
            LogUtils.d("数据load:" + result);
            UIUtils.runInMainThread(new Runnable() {
                @Override
                public void run() {
                    LogUtils.d("UIUtils.runInMainThread方法被执行");
                    mState =  result.getValue();
                    showPagerView();
                }
            });

        }
    }

    public enum LoadResult{
        ERROR(3),EMPTY(4),SUCCESS(5);
        int value;
        LoadResult(int value){
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
    }

    private View createErrorView() {
        return UIUtils.inflate(R.layout.loading_page_error);
    }

    private View createEmptyView() {
        return UIUtils.inflate(R.layout.loading_page_empty);
    }

    private View createLoadingView() {
        return UIUtils.inflate(R.layout.loading_page_loading);
    }
}
