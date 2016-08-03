package com.itheima.elecmarket.fragment;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.itheima.elecmarket.R;
import com.itheima.elecmarket.application.utils.DrawableUtils;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.protocol.HotProtocol;
import com.itheima.elecmarket.ui.widget.FlowLayout;
import com.itheima.elecmarket.ui.widget.LoadingPager;


public class HotFragment extends BaseFragment {

	private List<String> mDatas;
	private TextView textView;
	private int red;
	private int green;
	private int blue;
	private int color;
	private GradientDrawable drawable;
	private FlowLayout flowLayout;
	private Random random;

	@Override
	protected View createSuccessView() {
		flowLayout = new FlowLayout(UIUtils.getContext());

		random = new Random();

		int spacing = UIUtils.dip2px(13);
		// 设置间距
		flowLayout.setVerticalSpacing(spacing);
		flowLayout.setHorizontalSpacing(spacing);
		// 设置左上右下的间距
		flowLayout.setPadding(spacing, spacing, spacing, spacing);

		for (int i = 0; i < mDatas.size(); i++) {

			textView = new TextView(UIUtils.getContext());

			red = 20 + random.nextInt(220);
			green = 20 + random.nextInt(220);
			blue = 20 + random.nextInt(220);
			color = Color.rgb(red, green, blue);

			drawable = new DrawableUtils().createDrawable(color, color, 5);

			// 设置字体的数据
			textView.setText(mDatas.get(i));
			// 设置字体的颜色
			textView.setTextColor(UIUtils.getColor(R.color.white));
			// 这个地方一定要使用过时的，不然没办法向下兼容
			textView.setBackgroundDrawable(drawable);
			// 设置字体的大小，单位是dip，如果控件是包括内容的话，就需要使用dip，不能使用sp
			textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
			flowLayout.addView(textView);
			mDatas.get(i);
		}
		return flowLayout;
	}

	@Override
	protected LoadingPager.LoadResult loadData() {
		HotProtocol protocol = new HotProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

}
