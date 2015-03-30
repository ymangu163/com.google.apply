package com.google.apply.holder;

import com.google.apply.R;
import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.utils.UIUtils;
import android.view.View;
import android.widget.RelativeLayout;

public class MoreHolder extends BaseHolder<Integer> {
	//表示有更多的数据类型
	public static final int HAS_MORE = 0;
	//表示没有更多的数据类型
	public static final int NO_MORE = 1;
	//加载失败
	public static final int ERROR = 2;
	private MyBaseAdapter adapter;
	private RelativeLayout rl_more_loading;
	private RelativeLayout rl_more_error;
	private View view;	
	
	public MoreHolder(MyBaseAdapter adapter, boolean hasMore){
		this.adapter = adapter;
		setData(hasMore ? HAS_MORE : NO_MORE);		
	}
	
	@Override
	public void refreshView() {
		Integer data = getData();
		rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.GONE);
		rl_more_error.setVisibility(data == ERROR ? View.VISIBLE : View.GONE);		
	}

	@Override
	public View initView() {
		view = UIUtils.inflate(R.layout.list_more_loading);
		rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
		rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);		
		return view;
	}
	
	@Override
	public View getRootView() {
		if(getData() == HAS_MORE){
			loadMore();
		}	
		return super.getRootView();
	}

	private void loadMore() {
		if(adapter != null){
			adapter.loadMore();
		}
	}
	
}
