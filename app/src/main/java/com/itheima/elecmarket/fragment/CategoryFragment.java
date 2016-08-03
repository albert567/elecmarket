package com.itheima.elecmarket.fragment;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itheima.elecmarket.adapter.MyBaseAdapter;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.CategoryInfo;
import com.itheima.elecmarket.holder.BaseHolder;
import com.itheima.elecmarket.holder.CategoryHolder;
import com.itheima.elecmarket.holder.CategoryTitleHolder;
import com.itheima.elecmarket.protocol.CategoryProtocol;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class CategoryFragment extends BaseFragment {

	private List<CategoryInfo> mDatas;

	@Override
	protected LoadingPager.LoadResult loadData() {
		CategoryProtocol protocol = new CategoryProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createSuccessView() {
		ListView mListView = new ListView(UIUtils.getContext());
		CategoryAdapter adapter = new CategoryAdapter(mDatas);
		mListView.setAdapter(adapter);
		return mListView;
	}

	private class CategoryAdapter extends MyBaseAdapter<CategoryInfo> {

		public CategoryAdapter(List<CategoryInfo> lists) {
			super(lists);
		}

		int position = 0;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			this.position = position;
			return super.getView(position, convertView, parent);
		}

		@Override
		protected BaseHolder getHolder() {
			if (getData().get(position).isTitle()) {
				return new CategoryTitleHolder();
			} else {
				return new CategoryHolder();
			}
		}

		@Override
		public int getInnerItemViewType(int position) { // getItemViewType
			if (getData().get(position).isTitle()) {
				return super.getInnerItemViewType(position) + 1; // getItemViewType
			} else {
				return super.getInnerItemViewType(position); // getItemViewType
			}
		}

		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount() + 1;
		}

		@Override
		protected List onLoadMore() {
			return null;
		}

		@Override
		public boolean hasmore() {
			return false;
		}
	}
}
