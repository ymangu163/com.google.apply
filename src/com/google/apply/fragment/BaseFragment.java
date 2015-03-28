package com.google.apply.fragment;

import java.util.List;

import com.google.apply.ui.widget.LoadingPager;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {
	private LoadingPager mContentView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (null == mContentView) {
			mContentView = new LoadingPager(UIUtils.getContext()){

				@Override
				protected View createSuccessView() {
					return BaseFragment.this.createLoadedView();
				}

				@Override
				public LoadResult load() {
					return BaseFragment.this.load();
				}				
			};
		}
		
		return mContentView;
	}
	//	加载数据
	protected abstract LoadResult load();
	
	//加载View
	protected abstract View createLoadedView();
	
	
	// 展示具体的页面
		public void show() {
			if (null != mContentView) {
				mContentView.show();
			}

		}
	
		// 检查服务器返回的数据情况
		public LoadResult check(Object obj) {
			if (obj == null) {
				return LoadResult.ERROR;
			}
			if (obj instanceof List) {
				List list = (List) obj;
				if (list.size() == 0) {
					return LoadResult.EMPTY;
				}

			}

			return LoadResult.SUCCEED;
		}
}
