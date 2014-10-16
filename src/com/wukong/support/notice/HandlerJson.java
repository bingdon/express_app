package com.wukong.support.notice;

import org.json.JSONObject;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;

public abstract class HandlerJson extends JsonHttpResponseHandler {
	private Context context;
	private String message;
	private int id;
	public HandlerJson(Context context,String message,int id){
		this.context=context;
		this.message=message;
		this.id=id;
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		NoticeUtils.notice(context, message, id);
	}
	
	
	@Override
	public void onFailure(Throwable e, JSONObject errorResponse) {
		// TODO Auto-generated method stub
		super.onFailure(e, errorResponse);
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
