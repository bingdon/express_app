package com.wukong.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.adapter.RouteAdapter;
import com.wukong.bean.OrderBean;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.support.debug.AppLog;
import com.wukong.utils.WKHttpClient;

public class RouteActivity extends Activity implements OnClickListener {
	/************* 标题栏 **************/
	private static final String TAG = RouteActivity.class.getSimpleName();
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private ListView routelist;
	private RouteAdapter routeadapter;
	private List<OrderBean> orderBeans = new ArrayList<OrderBean>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.route_activity);
		titleView();
		routelist = (ListView) findViewById(R.id.route_listview);
		routeadapter = new RouteAdapter(RouteActivity.this,orderBeans);
		routelist.setAdapter(routeadapter);
		listToOrder();
	}

	private void titleView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		head_txt.setText("同城路线");
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

	private void listToOrder() {
		WKHttpClient.secExpress("e1", handler);
	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
			AppLog.w(TAG, "错误:" + errorResponse);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			parseJson(response);
			AppLog.i(TAG, "新订单:" + response);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

	};

	private void parseJson(JSONObject response) {
		try {
			if (1 == response.getInt("result")) {
				JSONObject object = response.getJSONObject("exp");
				JSONArray jsonArray = object.getJSONArray("resultlist");
				int length = jsonArray.length();
				for (int i = 0; i < length; i++) {
					OrderBean orderBean = GsonUtlity.getOrderBean(jsonArray
							.getJSONObject(i) + "");
					orderBeans.add(orderBean);
				}
				routeadapter.notifyDataSetChanged();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
