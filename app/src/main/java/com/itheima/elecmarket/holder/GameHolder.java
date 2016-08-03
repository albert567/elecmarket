package com.itheima.elecmarket.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.elecmarket.R;
import com.itheima.elecmarket.application.utils.StringUtils;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.AppInfo;
import com.itheima.elecmarket.imageload.ImageLoader;


public class GameHolder extends BaseHolder<AppInfo> {
	private TextView item_title;
	private RatingBar item_rating;
	private TextView item_size;
	private TextView item_bottom;
	private ImageView item_icon;

	@Override
	protected View initView() {

		View view = UIUtils.inflate(R.layout.list_item);

		item_icon = (ImageView) view.findViewById(R.id.item_icon);
		item_title = (TextView) view.findViewById(R.id.item_title);
		item_rating = (RatingBar) view.findViewById(R.id.item_rating);
		item_size = (TextView) view.findViewById(R.id.item_size);
		item_bottom = (TextView) view.findViewById(R.id.item_bottom);

		return view;
	}

	@Override
	public void refreshView() {
		AppInfo appInfo = getData();
		ImageLoader.load(item_icon, appInfo.getIconUrl());
		item_title.setText(appInfo.getName());
		item_rating.setRating(appInfo.getStars());
		item_size.setText(StringUtils.formatFileSize(appInfo.getSize()));
		item_bottom.setText(appInfo.getDes());

	}

}
