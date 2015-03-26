package com.google.apply.fragment;

import java.util.HashMap;

public class FragmentFactory {
	private static final int Title_HOME = 0;
	private static final int Title_APP = 1;
	private static final int Title_GAME = 2;
	private static final int Title_SUBJECT = 3;
	private static final int Title_RECOMMENT = 4;
	private static final int Title_CATEGORY = 5;
	private static final int Title_HOT = 6;
	
	 //初始化缓存的HashMap
		private static HashMap<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();
		
		public static BaseFragment createFragment(int position) {
			BaseFragment fragment = mFragments.get(position);
			if(fragment == null){
				switch (position) {
				case Title_HOME:
		            fragment = new HomeFragment();
					break;

				case Title_APP:
		            fragment = new AppFragment();
					break;

				case Title_GAME:
		            fragment = new GameFragment();
					break;

				case Title_SUBJECT:
					fragment = new SubjectFragment();
					break;

				case Title_RECOMMENT:
		            fragment = new RecommentFragment();
					break;

				case Title_CATEGORY:
		            fragment = new CategoryFragment();
					break;

				case Title_HOT:
		              fragment = new HotFragment();
					break;
				
			}
				mFragments.put(position, fragment);
			}
			return fragment;
		}
}
