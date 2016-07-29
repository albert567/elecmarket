package com.itheima.elecmarket;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by zyp on 2016/7/29.
 */
public abstract class BaseActivity extends ActionBarActivity {
    //获取到前台进程的Activity
    private static Activity mForegroundActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initActionBar();
    }

    /**
     * 初始化数据
     */
    protected abstract void init();

    /**
     * 初始化界面
     */
    protected abstract void initView();

    /**
     * 初始化actionbar
     */
    protected abstract  void initActionBar();

    @Override
    protected void onResume() {
        super.onResume();
        mForegroundActivity = this;
    }

    public static Activity getForegroundActivity(){
        return mForegroundActivity;
    }

}
