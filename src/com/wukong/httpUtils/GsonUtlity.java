package com.wukong.httpUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wukong.bean.GoodsTypeBean;
import com.wukong.bean.OrderBean;
import com.wukong.bean.PersonInfoBean;
import com.wukong.support.debug.AppLog;

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
	
	public static GoodsTypeBean getGoodsTypeBean(String object) {
		GoodsTypeBean goodsTypeBean=new GoodsTypeBean();
		try {
			goodsTypeBean=new Gson().fromJson(object, GoodsTypeBean.class);
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
			AppLog.w(TAG, "´íÎó:"+e.getMessage());
		}
		return goodsTypeBean;
	}
	
	public static OrderBean getOrderBean(String object) {
		OrderBean orderBean=new OrderBean();
		try {
			orderBean=new Gson().fromJson(object, OrderBean.class);
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
			AppLog.w(TAG, "´íÎó:"+e.getMessage());
		}
		return orderBean;
	}
	
}
