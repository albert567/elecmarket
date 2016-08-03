package com.itheima.elecmarket.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.itheima.elecmarket.adapter.MyBaseAdapter;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.AppInfo;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.holder.HomeHolder;
import com.itheima.elecmarket.protocol.HomeProtocol;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class HomeFragment extends BaseFragment {

	// private ArrayList<String> mDatas;

	private List<AppInfo> mDatas;

	@Override
	protected View createSuccessView() {

		ListView mListView = new ListView(UIUtils.getContext());
		HomeAdapter adapter = new HomeAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	@Override
	protected LoadingPager.LoadResult loadData() {
		// HttpResult result = HttpHelper.get("xxxx");
		// String json = result.getString();
		// mDatas = new ArrayList<String>();
		// for (int i = 0; i < 100; i++) {
		// mDatas.add("我是item" + i);
		// }
		HomeProtocol protocol = new HomeProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	private class HomeAdapter extends MyBaseAdapter<AppInfo> {

		public HomeAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		protected BaseHolder getHolder() {
			return new HomeHolder();
		}

		@Override
		protected List onLoadMore() {
			HomeProtocol protocol = new HomeProtocol();
			return protocol.load(getData().size());
		}

	}

	// private class ViewHolder extends BaseHolder<String> {
	//
	// TextView tv;
	//
	// @Override
	// protected View initView() {
	// // 创建并返回布局文件
	// tv = new TextView(UIUtils.getContext());
	// return tv;
	// }
	//
	// @Override
	// public void refreshView() {
	// tv.setText(getData());
	// }
	// }
}
