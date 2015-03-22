package com.google.apply.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public abstract class BaseActivity extends ActionBarActivity {
	 private Context context= null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context = this;
		initView();
		initActionbar();
	}
	
	protected abstract void initView() ;
	protected abstract void initActionbar() ;
}
