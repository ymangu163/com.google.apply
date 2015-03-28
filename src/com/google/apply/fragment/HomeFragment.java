package com.google.apply.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.holder.BaseHolder;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class HomeFragment extends BaseFragment {
	private List<String> mDatas;
	@Override
	protected LoadResult load() {
		mDatas = new ArrayList<String>();
		for(int i = 0 ; i < 100 ;i++){
			mDatas.add("我是item"+ i);
		}
		
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		HomeAdapter adapter = new HomeAdapter(mDatas);
		mListView.setAdapter(adapter);
		
		return mListView;
	}

	private class HomeAdapter extends MyBaseAdapter{

		public HomeAdapter(List<String> mDatas) {
			super(mDatas);
		}

		@Override
		public BaseHolder getHolder() {
			return new ViewHolder();
		}
		

		
	
	}
	
	private class ViewHolder extends BaseHolder<String>{
		TextView tv;

		@Override
		public void refreshView() {
			tv.setText(getData());
		}

		@Override
		public View initView() {
			tv = new TextView(UIUtils.getContext());
			return tv;
		}
	}
}
