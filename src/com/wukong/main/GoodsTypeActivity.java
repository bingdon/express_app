package com.wukong.main;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.adapter.GoodsTypeAdapter;
import com.wukong.bean.GoodsTypeBean;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.utils.WKHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GoodsTypeActivity extends Activity implements OnClickListener, OnItemClickListener {
	/************* 标题栏 **************/
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private ListView mListView;
	private GoodsTypeAdapter goodsTypeAdapter;
	private List<GoodsTypeBean> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goodstype_activity);
		TitleView();
		getgoods();
	}

	private void getgoods() {
		// TODO Auto-generated method stub
		list = new ArrayList<GoodsTypeBean>();
		goodsTypeAdapter = new GoodsTypeAdapter(list, this);
		mListView.setAdapter(goodsTypeAdapter);
		WKHttpClient.listGoodsType(handler);
		mListView.setOnItemClickListener(this);
	}

	private void TitleView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		mListView = (ListView) findViewById(R.id.son_listview);
		head_txt.setText("物品类型");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		default:
			break;
		}
	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
		public void onStart() {
			super.onStart();
		};

		public void onFailure(Throwable e, org.json.JSONObject errorResponse) {
			super.onFailure(e, errorResponse);
		};

		public void onSuccess(int statusCode, org.json.JSONObject response) {
			super.onSuccess(statusCode, response);
			parseJson(response);
		};
	};

	private void parseJson(JSONObject response){
		try {
			if (1==response.getInt("result")) {
				JSONObject object=response.getJSONObject("addlist");
				JSONArray jsonArray=object.getJSONArray("resultlist");
				int length=jsonArray.length();
				for (int i = 0; i < length; i++) {
					JSONObject jObject=jsonArray.getJSONObject(i);
					GoodsTypeBean goodsTypeBean=GsonUtlity.getGoodsTypeBean(jObject.toString());
					list.add(goodsTypeBean);
				}
				goodsTypeAdapter.notifyDataSetChanged();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.putExtra("name", list.get(arg2).getName());
		setResult(RESULT_OK, intent);
		finish();
	}
	
}
