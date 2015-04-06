package com.google.apply.holder;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.apply.R;
import com.google.apply.bean.AppInfo;
import com.google.apply.image.ImageLoader;
import com.google.apply.utils.UIUtils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public class DetailSafeHolder extends BaseHolder<AppInfo> implements OnClickListener {

	private ImageView safe_arrow;
	private LinearLayout safe_content;
	private RelativeLayout safe_layout;
	private ImageView[] ivs;
	private TextView[] tvs;
	private ImageView[] des_ivs;
	private LinearLayout[] mLinearLayouts;
	
	@Override
	public void onClick(View v) {
		final LayoutParams params = safe_content.getLayoutParams();
		int height = safe_content.getMeasuredHeight();
		boolean tag = (Boolean) safe_arrow.getTag();
		int tagetHeight = 0;
		if(tag){
			safe_arrow.setTag(false);
			tagetHeight = 0;
		}else{
			safe_arrow.setTag(true);
			tagetHeight = measureContentHeight();
		}
		safe_layout.setEnabled(false);           
		ValueAnimator va = ValueAnimator.ofInt(height,tagetHeight);
		//设置动画的更新监听器,取出每一次变化的高度
		va.addUpdateListener(new AnimatorUpdateListener(){

			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				params.height = (Integer) arg0.getAnimatedValue();
				safe_content.setLayoutParams(params);
			}	
			
		});
		
		//防止用户不停的点击。
		va.addListener(new AnimatorListener() {

			@Override
			public void onAnimationCancel(Animator arg0) {
				
			}

			@Override
			public void onAnimationEnd(Animator arg0) {
			    safe_layout.setEnabled(true);
				boolean tag = (Boolean) safe_arrow.getTag();
				safe_arrow.setImageResource(tag ? R.drawable.arrow_up : R.drawable.arrow_down);
			}

			@Override
			public void onAnimationRepeat(Animator arg0) {
				
			}

			@Override
			public void onAnimationStart(Animator arg0) {
		
			}
			
		});
		va.setDuration(300);
		va.start();
		
		
	}

	private int measureContentHeight() {
		int width = safe_content.getMeasuredWidth();
		safe_content.getLayoutParams().height = LayoutParams.WRAP_CONTENT;
		int widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		int heightMeasureSpec = MeasureSpec.makeMeasureSpec(20000, MeasureSpec.AT_MOST);
		safe_content.measure(widthMeasureSpec, heightMeasureSpec);
		return safe_content.getMeasuredHeight();
	}

	@Override
	public void refreshView() {

		AppInfo info = getData();
		// 对应着官方。安全。无广告等的图片下载地址
		List<String> safeUrl = info.getSafeUrl();
		// 小框框打勾的下载地址
		List<String> safeDesUrl = info.getSafeDesUrl();
		// 小框框打勾后面的描述信息
		List<String> safeDes = info.getSafeDes();
		// 描述的文字颜色，有广告的颜色比较醒目
		List<Integer> safeDesColor = info.getSafeDesColor();
		for (int i = 0; i < 4; i++) {
			if (i < safeUrl.size() && i < safeDesUrl.size() && i < safeDes.size() && i < safeDesColor.size()) {
				ImageLoader.load(ivs[i], safeUrl.get(i));
				ImageLoader.load(des_ivs[i], safeDesUrl.get(i));
				tvs[i].setText(safeDes.get(i));
				int color;
				int colorType = safeDesColor.get(i);
				if (colorType >= 1 && colorType <= 3) {
					color = Color.rgb(255, 153, 0);
				} else if (colorType == 4) {
					color = Color.rgb(0, 177, 62);
				} else {
					color = Color.rgb(122, 122, 122);
				}
				tvs[i].setTextColor(color);

				ivs[i].setVisibility(View.VISIBLE);
				mLinearLayouts[i].setVisibility(View.VISIBLE);		
			
			}else {
				ivs[i].setVisibility(View.GONE);
				mLinearLayouts[i].setVisibility(View.GONE);
			}
		}
		
		
		
		
		
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.app_detail_safe);
		safe_layout = (RelativeLayout) view.findViewById(R.id.safe_layout);
		safe_layout.setOnClickListener(this);
		safe_arrow = (ImageView) view.findViewById(R.id.safe_arrow);
		safe_arrow.setTag(false);
		
		 safe_content = (LinearLayout) view.findViewById(R.id.safe_content);
		 safe_content.getLayoutParams().height = 0;
		 
		 ivs = new ImageView[4];
		 ivs[0] = (ImageView) view.findViewById(R.id.iv_1);
	     ivs[1]= (ImageView) view.findViewById(R.id.iv_2);
	     ivs[2] = (ImageView) view.findViewById(R.id.iv_3);
	     ivs[3] = (ImageView) view.findViewById(R.id.iv_4);
	     
		des_ivs = new ImageView[4];
		des_ivs[0] = (ImageView) view.findViewById(R.id.des_iv_1);
		des_ivs[1] = (ImageView) view.findViewById(R.id.des_iv_2);
		des_ivs[2] = (ImageView) view.findViewById(R.id.des_iv_3);
		des_ivs[3] = (ImageView) view.findViewById(R.id.des_iv_4);
		
		tvs = new TextView[4];
		tvs[0] = (TextView) view.findViewById(R.id.des_tv_1);
		tvs[1] = (TextView) view.findViewById(R.id.des_tv_2);
		tvs[2] = (TextView) view.findViewById(R.id.des_tv_3);
		tvs[3] = (TextView) view.findViewById(R.id.des_tv_4);

		mLinearLayouts = new LinearLayout[4];
		mLinearLayouts[0] = (LinearLayout) view.findViewById(R.id.des_layout_1);
		mLinearLayouts[1] = (LinearLayout) view.findViewById(R.id.des_layout_2);
		mLinearLayouts[2] = (LinearLayout) view.findViewById(R.id.des_layout_3);
		mLinearLayouts[3] = (LinearLayout) view.findViewById(R.id.des_layout_4);
		return view;
	}

}
