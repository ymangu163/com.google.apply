package com.google.apply.fragment;

import java.util.List;

import android.view.View;
import android.widget.ListView;

import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.bean.SubjectInfo;
import com.google.apply.holder.BaseHolder;
import com.google.apply.holder.SubjectHolder;
import com.google.apply.protocol.SubjectProtocol;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class SubjectFragment extends BaseFragment {
	private List<SubjectInfo> mDatas;
	@Override
	protected LoadResult load() {
		SubjectProtocol protocol = new SubjectProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		SubjectAdapter adapter = new SubjectAdapter(mDatas);
		mListView.setAdapter(adapter);		
		return mListView;
	}

	
	private class SubjectAdapter extends MyBaseAdapter<SubjectInfo>{

		public SubjectAdapter(List<SubjectInfo> mDatas) {
			super(mDatas);

		}

		@Override
		public BaseHolder getHolder() {
			return new SubjectHolder();
		}

		@Override
		protected List onLoadMore() {
			SubjectProtocol protocol = new SubjectProtocol();
			return protocol.load(getData().size());
		}
	
		
		
		
	}
}
