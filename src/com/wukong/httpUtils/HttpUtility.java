package com.wukong.httpUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtility {

	public static boolean isSuccess(JSONObject object) {
		boolean res = false;
		try {
			if (object.getInt("result") == 1) {
				res = true;
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
