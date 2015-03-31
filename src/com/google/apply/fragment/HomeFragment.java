package com.google.apply.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.bean.AppInfo;
import com.google.apply.holder.BaseHolder;
import com.google.apply.holder.HomeHolder;
import com.google.apply.protocol.HomeProtocol;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class HomeFragment extends BaseFragment {
	
	private List<AppInfo> mDatas;
	
	@Override
	protected LoadResult load() {
		HomeProtocol protocol = new HomeProtocol();
		mDatas = protocol.load(0);
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
		public HomeAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		public BaseHolder getHolder() {			
			return new HomeHolder();
		}

		@Override
		protected List onLoadMore() {
			HomeProtocol protocol = new HomeProtocol();	
			return protocol.load(mDatas.size());
		}	
	}
}
