package com.google.apply.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.apply.protocol.RecommendProtocol;
import com.google.apply.random.StellarMap;
import com.google.apply.random.StellarMap.Adapter;
import com.google.apply.ui.widget.LoadingPager.LoadResult;
import com.google.apply.utils.UIUtils;

public class RecommentFragment extends BaseFragment {
	private List<String> mDatas;
	private StellarMap stellarMap;
	
	@Override
	protected LoadResult load() {
		RecommendProtocol protocol = new RecommendProtocol();
		mDatas = protocol.load(0);

		return check(mDatas);
	}

	@Override
	protected View createLoadedView() {
		stellarMap = new StellarMap(UIUtils.getContext());
		// 设置间距
		stellarMap.setInnerPadding(20, 20, 20, 20);
		// 设置x和y的规则
		stellarMap.setRegularity(6, 9);
		
		StellarAdapter adapter = new StellarAdapter();
		//设置数据适配器
		stellarMap.setAdapter(adapter);
		//从第0组开始加载数据，然后开启动画
		stellarMap.setGroup(0, true);	
		
		return stellarMap;
	}

	private class StellarAdapter implements Adapter{

		 private Random random;
		 public StellarAdapter() {
				super();
				//初始化随机数
				random = new Random();
			}
		 
		//设置2组规则
		@Override
		public int getGroupCount() {
			return 2;
		}
		 //每一组表示有多少个数
		@Override
		public int getCount(int group) {
			return 15;
		}

		@Override
		public View getView(int group, int position, View convertView) {
			TextView textView = new TextView(UIUtils.getContext());
			//随机颜色
			int red = 20 + random.nextInt(220);
			int green = 20 + random.nextInt(220);
			int blue = 20 + random.nextInt(220);
			//合成一个颜色
			int color = Color.rgb(red, green, blue);
			//设置文字的颜色
			textView.setTextColor(color);
			//设置随便的字体大小
			textView.setTextSize(10 + random.nextInt(15));
			//设置数据
			textView.setText(mDatas.get(position));
			
			return textView;
		}

		@Override
		public int getNextGroupOnPan(int group, float degree) {
			return (group + 1) % 2;
		}

		@Override
		public int getNextGroupOnZoom(int group, boolean isZoomIn) {
			return (group + 1) % 2;
		}
		
		
		
	}
	
	
}
