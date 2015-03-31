package com.google.apply.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.bean.AppInfo;
import com.google.apply.holder.BaseHolder;
import com.google.apply.holder.GameHolder;
import com.google.apply.protocol.GameProtocol;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class GameFragment extends BaseFragment {
	private List<AppInfo> mDatas;
	
	@Override
	protected LoadResult load() {
		GameProtocol protocol = new GameProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		GameAdapter adapter = new GameAdapter(mDatas);
		mListView.setAdapter(adapter);
		
		return mListView;
	}
	
	private class GameAdapter extends MyBaseAdapter<AppInfo>{

		public GameAdapter(List<AppInfo> mDatas) {
			super(mDatas);
		}

		@Override
		public BaseHolder getHolder() {
			
			return new GameHolder();
		}

		@Override
		protected List onLoadMore() {
			GameProtocol protocol = new GameProtocol();
			return protocol.load(getData().size());
		}
		
	}
	
	
	
}
