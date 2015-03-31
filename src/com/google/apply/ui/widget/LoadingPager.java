package com.google.apply.ui.widget;

import com.google.apply.R;
import com.google.apply.mananger.ThreadManager;
import com.google.apply.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class LoadingPager extends FrameLayout {
	   //加载默认的状态
		private static final int STATE_UNLOADING = 1;
		//加载的状态
		private static final int STATE_LOADING = 2;
		//加载失败的状态
		private static final int STATE_ERROR = 3;
		//加载空的状态
		private static final int STATE_EMPTY = 4;
		//加载成功的状态
		private static final int STATE_SUCCESS = 5;		
		
		private int mState ;//默认的状态
		private View loadingView;//转圈的view
		private View errorView;//错误的view
		private View emptyView;//空的view
		private View successView;//成功的view
	
		public LoadingPager(Context context) {
			super(context);
			init();
		}	
		public LoadingPager(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}	
	public LoadingPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		// 初始化默认的状态
		mState = STATE_UNLOADING;
		
		loadingView = createLoadingView();		
		if (null != loadingView) {
			addView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		}

		errorView = createErrorView();
		if(null != errorView){
			   addView(errorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));	
		}
			
		emptyView = createEmptyView();		
		if(null != emptyView){
			addView(emptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		
		showSafePagerView();
	}
	
	private void showSafePagerView() {
		UIUtils.runInMainThread(new Runnable() {		//保证在UI线程中运行
			@Override
			public void run() {
				showPagerView();
				
			}
		});
		
	}
	
	protected void showPagerView() {
		if(null != loadingView){
			loadingView.setVisibility(mState == STATE_UNLOADING || mState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
		}
		if(null != errorView){
			errorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
		}
		
		if(null != emptyView){
			emptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
		}
		
		if(null == successView && mState == STATE_SUCCESS){
			successView = createSuccessView();
			addView(successView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		}
		
		if(null != successView){
			successView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
		}
		
	}
	
	public void show(){
		if(mState == STATE_ERROR || mState == STATE_EMPTY){
			mState = STATE_UNLOADING;
		}
		if(mState == STATE_UNLOADING){
			mState = STATE_LOADING;
			
		   ThreadManager manager = new ThreadManager();
			TaskRunnable task = new TaskRunnable();
		   manager.getLongPool().execute(task);

//			ExecutorService executorService = Executors.newFixedThreadPool(5);
//			TaskRunnable task = new TaskRunnable();
//			executorService.execute(task);
		}
	}
	
	private class TaskRunnable implements Runnable{

		@Override
		public void run() {
			  final LoadResult result =  load();
			  UIUtils.runInMainThread(new Runnable() {				
				@Override
				public void run() {
					mState = result.getValue();  //更新状态，因为是enum类型，保证只有一个状态
					showPagerView();
					
				}
			});
		}
		
	}
	
	
	// 梅举
	public  enum LoadResult{
		ERROR(3),EMPTY(4),SUCCEED(5);
        int value ;
		LoadResult(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
		
	}  
	
	protected abstract View createSuccessView();
	public abstract LoadResult load();    
	
	private View createLoadingView() {		
		return UIUtils.inflate(R.layout.loading_page_loading);
	}
	
	private View createEmptyView() {
		return UIUtils.inflate(R.layout.loading_page_empty);
	}
	
	private View createErrorView() {
		return UIUtils.inflate(R.layout.loading_page_error);
	}
	
	
	
}
