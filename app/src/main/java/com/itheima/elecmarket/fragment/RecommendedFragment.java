package com.itheima.elecmarket.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.protocol.RecommendProtocol;
import com.itheima.elecmarket.randomLayout.StellarMap;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class RecommendedFragment extends BaseFragment {

	private List<String> mDatas;
	private StellarMap stellarMap;

	@Override
	protected View createSuccessView() {
		stellarMap = new StellarMap(UIUtils.getContext());
		// 设置间距
		stellarMap.setInnerPadding(20, 20, 20, 20);
		// 设置x轴和y轴
		stellarMap.setRegularity(6, 9);

		StellAdapter adapter = new StellAdapter();

		stellarMap.setAdapter(adapter);
		// 从第0组开始加载数据，然后开启动画
		stellarMap.setGroup(0, true);
		return stellarMap;
	}

	@Override
	protected LoadingPager.LoadResult loadData() {

		RecommendProtocol protocol = new RecommendProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	private class StellAdapter implements StellarMap.Adapter {

		private Random random;
		private int red;
		private int green;
		private int blue;
		private int color;

		// 设置2组规则
		@Override
		public int getGroupCount() {
			return 2;
		}

		public StellAdapter() {
			super();
			random = new Random();
		}

		// 每一组表示有多少个数
		@Override
		public int getCount(int group) {
			return 15;
		}

		@Override
		public View getView(int group, int position, View convertView) {
			TextView textView = new TextView(UIUtils.getContext());

			/**
			 * 做为类变量，而不是在方法中总是重复的创建
			 */
			red = 20 + random.nextInt(220);
			green = 20 + random.nextInt(220);
			blue = 20 + random.nextInt(220);
			// 合成一个颜色
			color = Color.rgb(red, green, blue);
			// 随机出来一个颜色
			// int color=20+random.nextInt(230);
			textView.setTextColor(color);
			// 设置数据
			textView.setText(mDatas.get(position));

			return textView;
		}

		@Override
		public int getNextGroupOnPan(int group, float degree) {
			return (group + 1) % 2;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			return (group + 1) % 2;
		}

	}
}
