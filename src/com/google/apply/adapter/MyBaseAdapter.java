package com.google.apply.adapter;

import java.util.List;

import com.google.apply.holder.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter{

	public MyBaseAdapter(List<T> mDatas) {
		setData(mDatas);
	}
	 public List<T> mDatas;
	private BaseHolder holder;
	private void setData(List<T> mDatas) {
		this.mDatas = mDatas;
	}

	public List<T> getData(){
		return mDatas;
	}
	
	
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
		if(convertView != null){
			holder = (BaseHolder) convertView.getTag();
		}else{
			holder = getHolder();
		}
		holder.setData(mDatas.get(position));
		return holder.getRootView();
	
	}
	public abstract BaseHolder getHolder();
}
