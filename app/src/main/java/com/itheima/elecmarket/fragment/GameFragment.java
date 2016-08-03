package com.itheima.elecmarket.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.itheima.elecmarket.adapter.MyBaseAdapter;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.AppInfo;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.holder.GameHolder;
import com.itheima.elecmarket.protocol.GameProtocol;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class GameFragment extends BaseFragment {

	private List<AppInfo> mDatas;

	@Override
	protected View createSuccessView() {
		ListView mListView = new ListView(UIUtils.getContext());
		GameAdapter adapter = new GameAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	@Override
	protected LoadingPager.LoadResult loadData() {
		GameProtocol protocol = new GameProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	private class GameAdapter extends MyBaseAdapter<AppInfo> {

		public GameAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		protected BaseHolder getHolder() {

			return new GameHolder();
		}

		@Override
		protected List onLoadMore() {
			GameProtocol protocol = new GameProtocol();

			return protocol.load(getData().size());
		}

	}
}
