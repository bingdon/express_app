package com.wukong.my;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.support.debug.AppLog;
import com.wukong.support.notice.NoticeUtils;
import com.wukong.utils.WKHttpClient;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyRouteActivity extends Activity implements OnClickListener {
	/************* 标题栏 **************/
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private EditText startAddressEditText;
	private EditText endAddressEditText;
	private TextView startTimeTextView;
	private TextView endTimeTextView;
	private EditText vehicleEditText;
	private EditText trainidEditText;
	private TextView arriveTimeTextView;
	private EditText recivieWayEditText;
	private EditText sendWayEditText;
	private TextView demandTextView;
	private Context context;
	private static final String TAG=MyRouteActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myroute_activity);
		context=this;
		TitleView();
	}

	private void TitleView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);

		startAddressEditText = (EditText) findViewById(R.id.route_from_address);
		endAddressEditText = (EditText) findViewById(R.id.route_to_address);
		startTimeTextView = (TextView) findViewById(R.id.route_form_time);
		endTimeTextView = (TextView) findViewById(R.id.route_to_time);
		vehicleEditText = (EditText) findViewById(R.id.route_tool);
		trainidEditText = (EditText) findViewById(R.id.route_train);
		arriveTimeTextView = (TextView) findViewById(R.id.route_departure_time);
		recivieWayEditText = (EditText) findViewById(R.id.route_reciver);
		sendWayEditText = (EditText) findViewById(R.id.route_send);
		demandTextView = (TextView) findViewById(R.id.route_require);

		head_txt.setText("发布行程");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		findViewById(R.id.myroute_submit).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:
			finish();
			break;
		case R.id.myroute_submit:
			publishRout();
			break;
		default:
			break;
		}
	}

	private void publishRout() {
		String startaddress = startAddressEditText.getText().toString();
		if (TextUtils.isEmpty(startaddress)) {
			startAddressEditText.setError(getString(R.string.null_notice));
			startAddressEditText.requestFocus();
			return;
		}
		String endAddress = endAddressEditText.getText().toString();
		if (TextUtils.isEmpty(endAddress)) {
			endAddressEditText.setError(getString(R.string.null_notice));
			endAddressEditText.requestFocus();
			return;
		}
		String vehicle = vehicleEditText.getText().toString();
		if (TextUtils.isEmpty(vehicle)) {
			vehicleEditText.setError(getString(R.string.null_notice));
			vehicleEditText.requestFocus();
			return;
		}
		String trainid = trainidEditText.getText().toString();
		if (TextUtils.isEmpty(trainid)) {
			trainidEditText.setError(getString(R.string.null_notice));
			trainidEditText.requestFocus();
			return;
		}
		String recivie = recivieWayEditText.getText().toString();
		if (TextUtils.isEmpty(recivie)) {
			recivieWayEditText.setError(getString(R.string.null_notice));
			recivieWayEditText.requestFocus();
			return;
		}

		String sendway = sendWayEditText.getText().toString();
		if (TextUtils.isEmpty(sendway)) {
			sendWayEditText.setError(getString(R.string.null_notice));
			sendWayEditText.requestFocus();
			return;
		}

		String startTime = startTimeTextView.getText().toString();
		String endTime = endTimeTextView.getText().toString();
		String arriveTime = arriveTimeTextView.getText().toString();
		String require = demandTextView.getText().toString();
		WKHttpClient.postSchedule(WKApplication.getInstance()
				.getPersonInfoBean().getIde(), startaddress, endAddress,
				vehicle, trainid, startTime, endTime, recivie, sendway,
				require, handler);
	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {
		public void onStart() {
			super.onStart();
			NoticeUtils.notice(context, getString(R.string.publish_rout), 0);
			finish();
		}

		@Override
		public void onFailure(Throwable e, JSONObject errorResponse) {
			// TODO Auto-generated method stub
			super.onFailure(e, errorResponse);
			NoticeUtils.removeNotice(0, context);
			NoticeUtils.showFailePublish(context);
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			AppLog.i(TAG, "发布行程:"+response);
			NoticeUtils.removeNotice(0, context);
			NoticeUtils.showSuccessfulNotification(context);
		};

	};

}
