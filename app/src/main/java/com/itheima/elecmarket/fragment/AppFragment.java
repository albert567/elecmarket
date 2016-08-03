package com.itheima.elecmarket.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.itheima.elecmarket.adapter.MyBaseAdapter;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.AppInfo;
import com.itheima.elecmarket.fragment.BaseFragment;
import com.itheima.elecmarket.holder.AppHolder;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.protocol.AppProtocol;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class AppFragment extends BaseFragment {

	private List<AppInfo> mDatas;

	@Override
	protected View createSuccessView() {

		ListView mListView = new ListView(UIUtils.getContext());
		AppAdapter adapter = new AppAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	@Override
	protected LoadingPager.LoadResult loadData() {
		AppProtocol protocol = new AppProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	private class AppAdapter extends MyBaseAdapter<AppInfo> {

		public AppAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		protected BaseHolder getHolder() {
			return new AppHolder();
		}

		@Override
		protected List onLoadMore() {
			AppProtocol protocol = new AppProtocol();
			return protocol.load(getData().size());
		}

	}
}
