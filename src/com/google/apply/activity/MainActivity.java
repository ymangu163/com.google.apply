package com.google.apply.activity;

import com.google.apply.R;
import com.google.apply.R.layout;
import com.google.apply.fragment.BaseFragment;
import com.google.apply.fragment.FragmentFactory;
import com.google.apply.ui.widget.PagerTab;
import com.google.apply.utils.UIUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity implements OnPageChangeListener {

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		// 初始化横着滚动的title
		PagerTab tabs = (PagerTab) findViewById(R.id.tabs);
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		// 初始化FragmentManager的时候注意，需要向下兼容。所以使用支持包里面的getSupportFragmentManager
		ViewPagerAdapter adapter = new ViewPagerAdapter(
				getSupportFragmentManager());
		// 设置数据
		pager.setAdapter(adapter);
		//绑定viewpager和横着滚动的title
	    tabs.setViewPager(pager);
	  //设置左右滑动的监听
	  	tabs.setOnPageChangeListener(this);
		
	}

	@Override
	protected void initActionbar() {
		ActionBar actionBar = getSupportActionBar();  //得到ActionBar
	}
	
	private class ViewPagerAdapter extends FragmentStatePagerAdapter{

		private String[] tab_names;

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
			tab_names = UIUtils.getStringArray(R.array.tab_names);
		}

		@Override
		public Fragment getItem(int position) {
			return FragmentFactory.createFragment(position);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return tab_names[position];
		}

		@Override
		public int getCount() {
			return tab_names.length;
		}
		
		
		
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int position) {
		BaseFragment  fragment = FragmentFactory.createFragment(position);
		fragment.show();   //使下载的线程运行起来
	}
	
	
	
}
