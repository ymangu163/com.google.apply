package com.google.apply.protocol;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.apply.bean.AppInfo;
import com.google.apply.utils.LogUtils;


public class HomeProtocol extends BaseProtocol<List<AppInfo>> {
	private List<String> mPictureUrl;

	@Override
	protected String getKey() {
		return "home";
	}

	public List<String> getPictureUrl() {
		return mPictureUrl;
	}

	@Override
	protected List<AppInfo> parseFromJson(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			mPictureUrl = new ArrayList<String>();
			JSONArray array = jsonObject.optJSONArray("picture");
			if(array != null){
				for (int i = 0; i < array.length(); i++) {
					mPictureUrl.add(array.getString(i));
				}
			}
			List<AppInfo> list = new ArrayList<AppInfo>();
			array = jsonObject.getJSONArray("list");
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				AppInfo info = new AppInfo();
				info.setId(obj.getLong("id"));
				info.setName(obj.getString("name"));
				info.setPackageName(obj.getString("packageName"));
				info.setIconUrl(obj.getString("iconUrl"));
				info.setStars(Float.valueOf(obj.getString("stars")));
				info.setSize(obj.getLong("size"));
				info.setDownloadUrl(obj.getString("downloadUrl"));
				info.setDes(obj.getString("des"));
				list.add(info);
			}
			return list;
		} catch (Exception e) {
			LogUtils.e(e);
			return null;
		}
	}

}
