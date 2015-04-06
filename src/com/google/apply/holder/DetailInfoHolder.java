package com.google.apply.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.apply.R;
import com.google.apply.bean.AppInfo;
import com.google.apply.image.ImageLoader;
import com.google.apply.utils.StringUtils;
import com.google.apply.utils.UIUtils;

public class DetailInfoHolder extends BaseHolder<AppInfo> {

	private ImageView item_icon;
	private TextView item_title;
	private RatingBar item_rating;
	private TextView item_download;
	private TextView item_version;
	private TextView item_date;
	private TextView item_size;
	
	@Override
	public void refreshView() {
		 //调用BaseHolder的方法得到数据，那数据在哪set的呢？在已经check有效数据后调用的createLoadedView()方法中
		AppInfo appInfo = getData();  
		ImageLoader.load(item_icon, appInfo.getIconUrl());
		item_title.setText(appInfo.getName());
		item_rating.setRating(appInfo.getStars());
		item_download.setText(appInfo.getDownloadNum());
		item_version.setText(appInfo.getVersion());
		item_date.setText(appInfo.getDate());
		item_size.setText(StringUtils.formatFileSize(appInfo.getSize()));		
		
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.app_detail_info);
		item_icon = (ImageView) view.findViewById(R.id.item_icon);
		item_title = (TextView) view.findViewById(R.id.item_title);
		item_rating = (RatingBar) view.findViewById(R.id.item_rating);
		item_download = (TextView) view.findViewById(R.id.item_download);
		item_version = (TextView) view.findViewById(R.id.item_version);
		item_date = (TextView) view.findViewById(R.id.item_date);
		item_size = (TextView) view.findViewById(R.id.item_size);		
		return view;
	}		

}
