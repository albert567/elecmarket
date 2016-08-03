package com.itheima.elecmarket.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.elecmarket.R;
import com.itheima.elecmarket.application.utils.UIUtils;
import com.itheima.elecmarket.bean.SubjectInfo;
import com.itheima.elecmarket.imageload.ImageLoader;


public class SubjectHolder extends BaseHolder<SubjectInfo> {

	private ImageView item_icon;
	private TextView item_tx;

	@Override
	protected View initView() {
		View view = UIUtils.inflate(R.layout.subject_item);
		item_icon = (ImageView) view.findViewById(R.id.item_icon);
		item_tx = (TextView) view.findViewById(R.id.item_txt);
		return view;
	}

	@Override
	public void refreshView() {
		SubjectInfo subjectInfo = getData();
		ImageLoader.load(item_icon, subjectInfo.getUrl());
		item_tx.setText(subjectInfo.getDes());

	}

}
