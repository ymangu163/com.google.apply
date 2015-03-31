package com.google.apply.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.bean.AppInfo;
import com.google.apply.holder.AppHolder;
import com.google.apply.holder.BaseHolder;
import com.google.apply.protocol.AppProtocol;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class AppFragment extends BaseFragment {
	private List<AppInfo> mDatas;
	
	@Override
	protected LoadResult load() {
		AppProtocol protocol = new AppProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		AppAdapter adapter = new AppAdapter(mDatas);
		mListView.setAdapter(adapter);		
		return mListView;
	}

	
	private class AppAdapter extends MyBaseAdapter<AppInfo>{

		public AppAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		public BaseHolder getHolder() {			
			return new AppHolder();
		}

		@Override
		protected List onLoadMore() {
			AppProtocol protocol = new AppProtocol();			
			return protocol.load(getData().size());
		}	
	}
}
