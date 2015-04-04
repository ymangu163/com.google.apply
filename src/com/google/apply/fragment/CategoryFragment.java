package com.google.apply.fragment;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.apply.adapter.MyBaseAdapter;
import com.google.apply.bean.CategoryInfo;
import com.google.apply.holder.BaseHolder;
import com.google.apply.holder.CategoryHolder;
import com.google.apply.holder.CategoryTitleHolder;
import com.google.apply.protocol.CategoryProtocol;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class CategoryFragment extends BaseFragment {
	private List<CategoryInfo> mDatas;
	
	
	@Override
	protected LoadResult load() {
		CategoryProtocol protocol = new CategoryProtocol();
		mDatas = protocol.load(0);
		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		ListView mListView = new ListView(UIUtils.getContext());
		
		CategoryAdapter adapter = new CategoryAdapter(mDatas);
		
		mListView.setAdapter(adapter);
		return mListView;
	}

	private class CategoryAdapter extends MyBaseAdapter<CategoryInfo>{

		private BaseHolder holder;
        private int position;
		
		public CategoryAdapter(List<CategoryInfo> mDatas) {
			super(mDatas);
	
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			this.position = position;
			return super.getView(position, convertView, parent);
		}
		
		private int getItemViewTypeInner() {
			CategoryInfo categoryInfo = getData().get(position);
			if(categoryInfo.isTitle()){
				return super.getItemViewTypeInner(position) + 1;
			}else{
				return super.getItemViewTypeInner(position);
			}
		}
		
		@Override
		public int getViewTypeCount() {
			return super.getViewTypeCount()+1;
		}
		
		@Override
		public BaseHolder getHolder() {
			CategoryInfo categoryInfo = getData().get(position);
			if(categoryInfo.isTitle()){
				holder = new CategoryTitleHolder();
			}else{
				holder = new CategoryHolder();
			}
			return holder;
		}

		@Override
		protected List onLoadMore() {
			return null;
		}
		
		@Override
		public boolean hasMore() {
			return false;
		}
		
		
		
	}
	
	
}
