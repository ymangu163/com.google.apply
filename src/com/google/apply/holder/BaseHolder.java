package com.google.apply.holder;

import android.view.View;
/*
 *  1.BaseHolder是一个自己写的普通的类，
 *  2.在构造方法中调用initView()虚函数，返回一个View,
 *  然后为这个view设置tag.
 *  3. 定义了getRootView方法返回这个view
 *  4. 定义了setData，getData方法传递数据
 *  总之，就是实现了得到view和data两部分，然后为调用 refreshView把data展示到view上
 */
public abstract class BaseHolder<T> {
	
	private View view;
	private T data;
	
	public BaseHolder() {
		view = initView();
		view.setTag(this);
	}
	
	public void setData(T data){
		this.data = data;
		refreshView();
	}
	
	public T getData(){
		return data;
	}
	
	public View getRootView(){
		return view;
	}
	
	public abstract void refreshView();
	public abstract View initView();
}
