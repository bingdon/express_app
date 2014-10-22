package com.wukong.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.adapter.TraceAdapter;
import com.wukong.bean.OrderBean;
import com.wukong.bean.PersonInfoBean;
import com.wukong.bean.RoutBean;
import com.wukong.httpUtils.GsonUtlity;
import com.wukong.httpUtils.HttpUtility;
import com.wukong.support.debug.AppLog;
import com.wukong.support.image.LoadImageUtility;
import com.wukong.support.notice.HandlerJson;
import com.wukong.support.notice.NoticeUtils;
import com.wukong.utils.WKHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 快件跟踪
 * 
 * @author lyl
 * @version 1.0
 * 
 */
public class TraceActivity extends Activity implements ActivityInitInterface,
		OnClickListener {

	private static final String TAG = TraceActivity.class.getSimpleName();
	private ListView traceListView;
	private TraceAdapter traceAdapter;
	private List<RoutBean> traceList = new ArrayList<RoutBean>();
	private Context context;
	private TextView head_txt;// 标题头内容
	private String id = "";
	private OrderBean orderBean;
	private TextView telTextView;
	private ImageView headImageView;
	private TextView confirDone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_trace);
		context = this;
		initView();
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		traceListView = (ListView) findViewById(R.id.trace_list);
		confirDone = (TextView) findViewById(R.id.headbar_right_txt);
		head_txt.setText(R.string.activity_trace_title);
		confirDone.setText(R.string.confir_done);
		View view = getLayoutInflater().inflate(R.layout.trace_head, null,
				false);
		traceListView.addHeaderView(view);
		headImageView = (ImageView) findViewById(R.id.head);
		telTextView = (TextView) findViewById(R.id.tel);
		findViewById(R.id.headbar_right_layout).setVisibility(View.VISIBLE);
		findViewById(R.id.headbar_left_layout).setVisibility(View.VISIBLE);
		findViewById(R.id.headbar_left_layout).setOnClickListener(this);
		findViewById(R.id.headbar_right_layout).setOnClickListener(this);
		traceAdapter = new TraceAdapter(traceList, context);
		traceListView.setAdapter(traceAdapter);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		orderBean = (OrderBean) getIntent().getSerializableExtra("order");
		if (null == orderBean) {
			return;
		}
		id = orderBean.getTid();
		if (!TextUtils.isEmpty(id)) {
			WKHttpClient.findByDid(id, handler);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch (id) {
		case R.id.headbar_left_layout:
			finish();
			break;
		case R.id.headbar_right_layout:
			confirDone();
			break;

		default:
			break;
		}
	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

		@Override
		public void onFailure(int statusCode, Header[] headers, Throwable e,
				JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(statusCode, headers, e, errorResponse);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			parseJson(response);
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
		}

	};

	private void parseJson(JSONObject response) {
		AppLog.i(TAG, "返回:" + response);
		if (response == null) {
			return;
		}

		if (HttpUtility.isSuccess(response)) {
			try {
				JSONArray jsonArray = response.getJSONArray("sch");
				PersonInfoBean personInfoBean = GsonUtlity
						.getPersonInfoBean(response.getJSONObject("user")
								.toString());
				LoadImageUtility.displayWebImage(WKHttpClient.IMAGER_URL
						+ personInfoBean.getHeadimage(), headImageView);
				telTextView.setText("联系电话:" + personInfoBean.getTel());
				int length = jsonArray.length();
				for (int i = 0; i < length; i++) {
					RoutBean routBean = GsonUtlity.getRoutBean(jsonArray
							.getJSONObject(i).toString());
					routBean.setBangName(personInfoBean.getUsername());
					traceList.add(routBean);
				}
				int complete = response.getInt("complete");
				if (complete == 1) {
					RoutBean routBean = new RoutBean();
					routBean.setComplete(complete);
					try {
						String completetime = response
								.getString("completetime");
						routBean.setCreatetime(completetime);
					} catch (Exception e) {
						// TODO: handle exception
					}
					traceList.add(0, routBean);
				}
				traceAdapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void confirDone() {
		if (orderBean == null) {
			return;
		}

		WKHttpClient.complete(id, new HandlerJson(TraceActivity.this,
				getString(R.string.confiring), 0) {

			@Override
			public void parseJson(JSONObject response) {
				// TODO Auto-generated method stub
				AppLog.i("提交返回:" + response);
				if (HttpUtility.isSuccess(response)) {
					NoticeUtils.showSuccessfulNotification(context);
				} else {
					NoticeUtils.showFailePublish(context);
				}
			}
		});

	}

}
