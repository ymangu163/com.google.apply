package com.google.apply.holder;

import com.google.apply.R;
import com.google.apply.bean.CategoryInfo;
import com.google.apply.image.ImageLoader;
import com.google.apply.utils.StringUtils;
import com.google.apply.utils.UIUtils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class CategoryHolder extends BaseHolder<CategoryInfo> {

	private ImageView iv_1;
	private ImageView iv_2;
	private ImageView iv_3;
	private TextView tv_1;
	private TextView tv_2;
	private TextView tv_3;
	@Override
	public void refreshView() {
		CategoryInfo categoryInfo = getData();
		String name1 = categoryInfo.getName1();
		String name2 = categoryInfo.getName2();
		String name3 = categoryInfo.getName3();
		
		String imageUrl1 = categoryInfo.getImageUrl1();
		String imageUrl2 = categoryInfo.getImageUrl2();
		String imageUrl3 = categoryInfo.getImageUrl3();
		
		if(StringUtils.isEmpty(name1)){
			tv_1.setText("");
			iv_1.setImageDrawable(null);
		}else{
			tv_1.setText(name1);
			ImageLoader.load(iv_1, categoryInfo.getImageUrl1());
		}
		
		if(StringUtils.isEmpty(name2)){
			tv_2.setText("");
			iv_2.setImageDrawable(null);
		}else{
			tv_2.setText(name2);
			ImageLoader.load(iv_2, categoryInfo.getImageUrl2());
		}
		
		if(StringUtils.isEmpty(name3)){
			tv_3.setText("");
			iv_3.setImageDrawable(null);
		}else{
			tv_3.setText(name3);
			ImageLoader.load(iv_3, categoryInfo.getImageUrl3());
		}
		
		
		
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.category_item);
		iv_1 = (ImageView) view.findViewById(R.id.iv_1);
		iv_2 = (ImageView) view.findViewById(R.id.iv_2);
		iv_3 = (ImageView) view.findViewById(R.id.iv_3);
		
		tv_1 = (TextView) view.findViewById(R.id.tv_1);
		tv_2 = (TextView) view.findViewById(R.id.tv_2);
		tv_3 = (TextView) view.findViewById(R.id.tv_3);
		return view;
	}

}
