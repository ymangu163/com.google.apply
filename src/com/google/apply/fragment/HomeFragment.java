package com.google.apply.fragment;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
		HomeAdapter adapter = new HomeAdapter();
		mListView.setAdapter(adapter);
		
		return mListView;
	}

	private class HomeAdapter extends BaseAdapter{
		private ViewHolder holder;
		@Override
		public int getCount() {
			return mDatas.size();
		}

		@Override
		public Object getItem(int position) {
			return mDatas.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView =  new TextView(UIUtils.getContext());
				holder = new ViewHolder();
				holder.tv = (TextView) convertView;
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(mDatas.get(position));			
			return convertView;
		}
	
	}
	
	private class ViewHolder{
		TextView tv;
	}
}
