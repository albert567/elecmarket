package com.itheima.elecmarket.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.itheima.elecmarket.adapter.MyBaseAdapter;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.SubjectInfo;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.holder.SubjectHolder;
import com.itheima.elecmarket.protocol.SubjectProtocol;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class SubjectFragment extends BaseFragment {
	private List<SubjectInfo> mDatas;

	@Override
	protected View createSuccessView() {

		ListView mListView = new ListView(UIUtils.getContext());
		SubjectAdapter adapter = new SubjectAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	@Override
	protected LoadingPager.LoadResult loadData() {
		SubjectProtocol protocol = new SubjectProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	private class SubjectAdapter extends MyBaseAdapter<SubjectInfo> {

		public SubjectAdapter(List<SubjectInfo> mDatas) {
			super(mDatas);
		}

		@Override
		protected BaseHolder getHolder() {
			// TODO Auto-generated method stub
			return new SubjectHolder();
		}

		@Override
		protected List onLoadMore() {
			SubjectProtocol protocol = new SubjectProtocol();
			return protocol.load(getData().size());
		}

	}
}
