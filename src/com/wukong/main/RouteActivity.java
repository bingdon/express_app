package com.wukong.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.adapter.RouteAdapter;
import com.wukong.adapter.RouteAdapter.RoutGetListener;
import com.wukong.bean.OrderBean;
import com.wukong.bean.PersonInfoBean;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.httpUtils.HttpUtility;
import com.wukong.support.debug.AppLog;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class RouteActivity extends Activity implements OnClickListener,
		OnRefreshListener2<ListView>, RoutGetListener {
	/************* 标题栏 **************/
	private static final String TAG = RouteActivity.class.getSimpleName();
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private PullToRefreshListView routelist;
	private RouteAdapter routeadapter;
	private List<OrderBean> orderBeans = new ArrayList<OrderBean>();
	private String json = "";
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.route_activity);
		titleView();
		routelist = (PullToRefreshListView) findViewById(R.id.route_listview);
		routeadapter = new RouteAdapter(RouteActivity.this, orderBeans);
		routelist.setAdapter(routeadapter);
		routelist.setOnRefreshListener(this);
		listToOrder();
		routeadapter.setListener(this);
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
			if (!routelist.isRefreshing()) {
				routelist.setRefreshing(false);
			}
		}

		public void onFinish() {
			super.onFinish();
			routelist.onRefreshComplete();
		};

	};

	private void parseJson(JSONObject response) {
		try {
			if (1 == response.getInt("result")) {
				if (json.equals(response + "")) {
					ToastUtils.showShort(RouteActivity.this, "没有新消息");
					return;
				}
				json = response + "";
				JSONObject object = response.getJSONObject("exp");
				JSONArray jsonArray = object.getJSONArray("resultlist");
				int length = jsonArray.length();
				for (int i = 0; i < length; i++) {
					OrderBean orderBean = GsonUtlity.getOrderBean(jsonArray
							.getJSONArray(i).getJSONObject(0) + "");
					PersonInfoBean personInfoBean = GsonUtlity
							.getPersonInfoBean(jsonArray.getJSONArray(i)
									.getJSONObject(1) + "");
					orderBean.setHeadimage(personInfoBean.getHeadimage());
					if (!dealOrder(orderBean)) {
						orderBeans.add(orderBean);
					}

				}
				routeadapter.notifyDataSetChanged();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		String label = DateUtils.formatDateTime(getApplicationContext(),
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		ToastUtils.showLong(RouteActivity.this, "刷新");
		listToOrder();
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		ToastUtils.showLong(RouteActivity.this, "载入");
	}

	private boolean dealOrder(OrderBean orderBean) {
		if (orderBeans.size() == 0) {
			return false;
		}
		int length = orderBeans.size();
		for (int i = 0; i < length; i++) {
			if (orderBeans.get(i).equals(orderBean)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void getOrder(int position) {
		// TODO Auto-generated method stub
		WKHttpClient.postTrade(WKApplication.getInstance().getPersonInfoBean()
				.getIde(), orderBeans.get(position).getId(), mHandler);
		this.position=position;
	}

	private JsonHttpResponseHandler mHandler=new JsonHttpResponseHandler(){

		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			AppLog.i(TAG, "抢单结果:"+response);
			parseGetOrder(response);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}
		
	};
	
	
	private void parseGetOrder(JSONObject response){
		if (HttpUtility.isSuccess(response)) {
			Intent intent=new Intent();
			intent.setClass(this, GetOrderSuccessActivity.class);
			intent.putExtra("tel", orderBeans.get(position).getS_tel());
			startActivity(intent);
			orderBeans.remove(position);
			routeadapter.notifyDataSetChanged();
		}else {
			ToastUtils.showLong(this, "抢单失败");
		}
	}
	
}
