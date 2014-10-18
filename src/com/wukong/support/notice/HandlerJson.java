package com.wukong.support.notice;

import org.apache.http.Header;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class HandlerJson extends JsonHttpResponseHandler {
	private Activity context;
	private String message;
	private int id;
	public HandlerJson(Activity context,String message,int id){
		this.context=context;
		this.message=message;
		this.id=id;
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		NoticeUtils.notice(context, message, id);
		context.finish();
	}
	
	@Override
	public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
		// TODO Auto-generated method stub
		super.onFailure(arg0, arg1, arg2, arg3);
		NoticeUtils.removeNotice(id, context);
		NoticeUtils.showFailePublish(context);
	}
	
	@Override
	public void onSuccess(int statusCode, JSONObject response) {
		// TODO Auto-generated method stub
		super.onSuccess(statusCode, response);
		NoticeUtils.removeNotice(id, context);
		parseJson(response);
	}
	
	
	public abstract void parseJson(JSONObject response);
	
	
}
