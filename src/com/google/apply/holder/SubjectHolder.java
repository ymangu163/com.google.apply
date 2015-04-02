package com.google.apply.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.apply.R;
import com.google.apply.bean.SubjectInfo;
import com.google.apply.image.ImageLoader;
import com.google.apply.utils.UIUtils;

public class SubjectHolder extends BaseHolder<SubjectInfo> {
	private ImageView item_icon;
	private TextView item_txt;

	@Override
	public void refreshView() {
		SubjectInfo subjectInfo = getData();		
		ImageLoader.load(item_icon, subjectInfo.getUrl());
		item_txt.setText(subjectInfo.getDes());
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.subject_item);
		item_icon = (ImageView) view.findViewById(R.id.item_icon);
		item_txt = (TextView) view.findViewById(R.id.item_txt);
		
		return view;
	}

}
