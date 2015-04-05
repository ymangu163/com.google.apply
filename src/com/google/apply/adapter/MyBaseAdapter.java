package com.google.apply.adapter;

import java.util.List;

import com.google.apply.holder.BaseHolder;
import com.google.apply.holder.MoreHolder;
import com.google.apply.mananger.ThreadManager;
import com.google.apply.utils.UIUtils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter{
	public  final int MORE_VIEW_TYPE = 0;
	public  final int ITEM_VIEW_TYPE = 1;
	
	public MyBaseAdapter(List<T> mDatas) {
		setData(mDatas);
	}
	 public List<T> mDatas;
	private BaseHolder holder;
	private MoreHolder moreHolder;
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
	public int getItemViewType(int position) {
		if(position == getCount() - 1){ //最后一个
			return MORE_VIEW_TYPE;   //赋值为加载更多			
		}else{
			return ITEM_VIEW_TYPE;
		}
		
	}
	
	public int getItemViewTypeInner(int position) {
		return ITEM_VIEW_TYPE;
	}

	@Override
	public int getViewTypeCount() {
		return super.getViewTypeCount()+1;   //现在是两种view类型
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView != null){
			holder = (BaseHolder) convertView.getTag();
		}else{  //这里要分几种情况了
			 if(MORE_VIEW_TYPE == getItemViewType(position)){
				 holder=getMoreHolder();   //得到另一种Holder
			 }else{
				 holder = getHolder();				 
				 holder.setData(mDatas.get(position));
			 }			
			
		}
		
		return holder.getRootView();
	
	}
	//表示更多的数据
	private BaseHolder getMoreHolder() {
		if(moreHolder==null){
			moreHolder = new MoreHolder(this,hasMore());
		}		
		return moreHolder;
	}
	
	private boolean is_load = false;
	/**
     * 加载更多的数据
     */
	public void loadMore(){
		if(!is_load){
			is_load = true;
			ThreadManager.getLongPool().execute(new Runnable() {
				@Override
				public void run() {
					final List list = onLoadMore();   //由子类来完成
					UIUtils.runInMainThread(new Runnable() {  //改变View，要在UI线程中

						@Override
						public void run() {
							if(list == null){
								getMoreHolder().setData(MoreHolder.ERROR);
							}else if(list.size() < 20){
								getMoreHolder().setData(MoreHolder.NO_MORE);
							}else{
								getMoreHolder().setData(MoreHolder.HAS_MORE);
							}
							
							if(null != list){
								if(null != mDatas){
									mDatas.addAll(list);
								}else{
									setData(list);
								}
							}
							notifyDataSetChanged();
							is_load = false;							
						}						
					});
					
				}
				
			});
			
		}
		
	}
	
	
	// 不直接写死成true，是为了在子类中重载就可以改变
	public boolean hasMore() {
		return true;
	}
	public abstract BaseHolder getHolder();
	protected abstract List onLoadMore();
}
