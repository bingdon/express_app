package com.wukong.httpUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wukong.bean.PersonInfoBean;
import com.wukong.debug.AppLog;

public class GsonUtlity {

	private static final String TAG=GsonUtlity.class.getSimpleName();
	
	public static PersonInfoBean getPersonInfoBean(String object) {
		PersonInfoBean personInfoBean=new PersonInfoBean();
		try {
			personInfoBean=new Gson().fromJson(object, PersonInfoBean.class);
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
			AppLog.w(TAG, "´íÎó:"+e.getMessage());
		}
		return personInfoBean;
	}
	
}
