package com.google.apply.activity;

import com.google.apply.R;
import com.google.apply.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initActionbar() {
		ActionBar actionBar = getSupportActionBar();  //得到ActionBar
	}
}
