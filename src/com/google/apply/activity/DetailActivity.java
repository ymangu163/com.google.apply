package com.google.apply.activity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.google.apply.R;
import com.google.apply.bean.AppInfo;
import com.google.apply.holder.DetailInfoHolder;
import com.google.apply.holder.DetailSafeHolder;
import com.google.apply.protocol.DetailProtocol;
import com.google.apply.ui.widget.LoadingPager;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.StringUtils;
import com.google.apply.utils.UIUtils;
import com.google.apply.utils.ViewUtils;

public class DetailActivity extends BaseActivity {
	private String packageName;
	private AppInfo appInfo;
	private LoadingPager mContentView;
	@Override
	protected void initView() {		
		Intent intent = getIntent();
		packageName = intent.getStringExtra("packageName");
		
		if(mContentView==null){
		mContentView = new LoadingPager(UIUtils.getContext()) {
			
			@Override
			public LoadResult load() {
				
				return DetailActivity.this.load();
			}
			@Override
			protected View createSuccessView() {

				return DetailActivity.this.createLoadedView();
			}
		};
		
	}else{
		ViewUtils.removeSelfFromParent(mContentView);
	}
		setContentView(mContentView);
		mContentView.show();
	}
	
	protected View createLoadedView() {
		View view = UIUtils.inflate(R.layout.activity_detail);
		
		FrameLayout detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
		DetailInfoHolder detailInfoHolder = new DetailInfoHolder();
		detailInfoHolder.setData(appInfo);
		detail_info.addView(detailInfoHolder.getRootView());
		
		//第二部分view,去中心化架构
		FrameLayout detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
		DetailSafeHolder detailSafeHolder = new DetailSafeHolder();
		detailSafeHolder.setData(appInfo);
		detail_safe.addView(detailSafeHolder.getRootView());
		
		return view;
	}

	protected LoadResult load() {
		DetailProtocol protocol = new DetailProtocol();
		protocol.setPackageName(packageName);
		appInfo = protocol.load(0);
		if(null == appInfo || StringUtils.isEmpty(packageName)){
			return LoadResult.ERROR;
		}
		return LoadResult.SUCCEED;
	}

	@Override
	protected void initActionbar() {
		
	}

}
