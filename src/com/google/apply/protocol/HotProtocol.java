package com.google.apply.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.google.apply.utils.LogUtils;


/**
 * Created by mwqi on 2014/6/8.
 */
public class HotProtocol extends BaseProtocol<List<String>> {
	@Override
	protected String getKey() {
		return "hot";
	}

	@Override
	protected List<String> parseFromJson(String json) {
		try {
			JSONArray array = new JSONArray(json);
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < array.length(); i++) {
				String str = array.optString(i);
				list.add(str);
			}
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}
}
