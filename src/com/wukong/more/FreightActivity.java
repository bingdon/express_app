package com.wukong.more;

import org.apache.http.Header;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.wukong.R;
import com.wukong.data.AddressModel;
import com.wukong.httpUtils.HttpUtility;
import com.wukong.main.GoodsTypeActivity;
import com.wukong.my.MyAddressActivity;
import com.wukong.support.debug.AppLog;
import com.wukong.utils.Constants;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FreightActivity extends Activity implements OnClickListener {

	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private Context context;
	/**
	 * 开始地址
	 */
	private EditText startAddressEditText;
	/**
	 * 目的地址
	 */
	private EditText endAddressEditText;
	/**
	 * 商品类型
	 */
	private TextView goodsTypeTextView;
	/**
	 * 商品质量
	 */
	private EditText goodsWeighText;
	private final int START_ADDRESS = 3;
	private final int END_ADDRESS = 4;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.freight_activity);
		context = this;
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		startAddressEditText = (EditText) findViewById(R.id.freight_from);
		endAddressEditText = (EditText) findViewById(R.id.freight_to);
		goodsTypeTextView = (TextView) findViewById(R.id.goods_name);
		goodsWeighText = (EditText) findViewById(R.id.goods_heights);
		head_txt.setText("运费查询");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		findViewById(R.id.relativeLayout1).setOnClickListener(this);
		findViewById(R.id.relativeLayout2).setOnClickListener(this);
		findViewById(R.id.freight_type).setOnClickListener(this);
		findViewById(R.id.freight_count).setOnClickListener(this);
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage(getString(R.string.cost_search_ing));
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		case R.id.relativeLayout1:
			getAddress(START_ADDRESS);
			break;
		case R.id.relativeLayout2:
			getAddress(END_ADDRESS);
			break;
		case R.id.freight_type:
			getGoodsType();
			break;
		case R.id.freight_count:
			searchPrice();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		if (requestCode == Constants.START_ACTIVITY.SEND_TO_TYPE) {
			String type = data.getStringExtra("name");
			if (!TextUtils.isEmpty(type)) {
				goodsTypeTextView.setText(type);
			}
		} else if (requestCode == START_ADDRESS) {
			AddressModel addressModel = (AddressModel) data
					.getSerializableExtra("address");
			startAddressEditText.setText(addressModel.getDetail());
		} else if (requestCode == END_ADDRESS) {
			AddressModel addressModel = (AddressModel) data
					.getSerializableExtra("address");
			endAddressEditText.setText(addressModel.getDetail());
		}
	}

	private void getAddress(int id) {
		Intent intent = new Intent();
		intent.setClass(context, MyAddressActivity.class);
		intent.putExtra("getaddress", true);
		if (id == START_ADDRESS) {
			startActivityForResult(intent, START_ADDRESS);
		} else {
			startActivityForResult(intent, END_ADDRESS);
		}
	}

	private void getGoodsType() {
		Intent typeintent = new Intent(context, GoodsTypeActivity.class);
		startActivityForResult(typeintent,
				Constants.START_ACTIVITY.SEND_TO_TYPE);
	}

	/**
	 * 运费查询
	 */
	private void searchPrice() {
		String start = startAddressEditText.getText().toString();
		if (TextUtils.isEmpty(start)) {
			findViewById(R.id.relativeLayout1).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, "请输入初始点");
			return;
		}
		String end = endAddressEditText.getText().toString();
		if (TextUtils.isEmpty(end)) {
			findViewById(R.id.relativeLayout2).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, "请输入终点");
			return;
		}
		String type = goodsTypeTextView.getText().toString();
		String weight = goodsWeighText.getText().toString();
		if (TextUtils.isEmpty(weight)) {
			findViewById(R.id.freight_height).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, "请输入质量");
			return;
		}

		WKHttpClient.cost(start, end, type, weight, handler);

	}

	private JsonHttpResponseHandler handler = new JsonHttpResponseHandler() {

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
			parseJson(response);
		}

		public void onFinish() {
			progressDialog.dismiss();
		};

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			progressDialog.show();
		}

	};

	private void parseJson(JSONObject response) {
		AppLog.i("查询返回:" + response);
		if (HttpUtility.isSuccess(response)) {

		}
	}

}
