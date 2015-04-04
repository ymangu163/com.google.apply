package com.google.apply.holder;

import com.google.apply.R;
import com.google.apply.bean.CategoryInfo;
import com.google.apply.utils.UIUtils;

import android.view.View;
import android.widget.TextView;


public class CategoryTitleHolder extends BaseHolder<CategoryInfo> {

	private TextView tv_title;

	@Override
	public void refreshView() {
		CategoryInfo categoryInfo = getData();
		tv_title.setText(categoryInfo.getTitle());
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.category_item_title);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		return view;
	}

}
