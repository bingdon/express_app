package com.wukong.my;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.login.LoginActivity;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class AddressAddActivity extends Activity implements OnClickListener {
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件

	/********** 新增地址内容 ***********/
	private EditText reciver;// 收件人
	private EditText tel;// 电话
	private EditText zipcode;// 邮编
	private EditText area;// 地区
	private EditText detail;// 详细地址
	private Button save;// 保存
	private String id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.addaddress_activity);
		id = WKApplication.getInstance().getPersonInfoBean().getId();
		InitView();
	}

	private void InitView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		reciver = (EditText) findViewById(R.id.receive_name);
		tel = (EditText) findViewById(R.id.receive_phonenum);
		zipcode = (EditText) findViewById(R.id.receive_ucode);
		area = (EditText) findViewById(R.id.receive_area);
		detail = (EditText) findViewById(R.id.receive_address);
		save = (Button) findViewById(R.id.add_address_save);
		save.setOnClickListener(this);
		head_txt.setText("添加地址");
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
		case R.id.add_address_save:// 保存键
			httpAddress();
			break;
		default:
			break;
		}
	}

	private void httpAddress() {
		// TODO Auto-generated method stub
		if (reciver.getText().toString() == null
				|| reciver.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "收件人不能为空！");
			return;
		}
		if (tel.getText().toString() == null
				|| tel.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "手机号不能为空！");
			return;
		}
		if (tel.getText().toString().trim().length() != 11) {
			ToastUtils.showShort(AddressAddActivity.this, "手机号必须是11位！");
			return;
		}
		if (zipcode.getText().toString() == null
				|| zipcode.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "邮编不能为空！");
			return;
		}

		if (zipcode.getText().toString().trim().length() != 6) {
			ToastUtils.showShort(AddressAddActivity.this, "邮编必须6位！");
			return;
		}
		if (area.getText().toString() == null
				|| area.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "所在地区不能为空！");
			return;
		}
		if (detail.getText().toString() == null
				|| detail.getText().toString().trim().equals("")) {
			ToastUtils.showShort(AddressAddActivity.this, "地址详情不能为空！");
			return;
		}
		postAddress(id, reciver.getText().toString().trim(), tel.getText()
				.toString().trim(), zipcode.getText().toString().trim(), area
				.getText().toString().trim(), detail.getText().toString()
				.trim());
	}

	private void postAddress(String id, String reciver, String tel,
			String zipcode, String area, String detail) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				JSONObject obj;
				try {
					obj = new JSONObject(response);
					Log.i("response-----result", obj.getString("result") + "");
					if (obj.getInt("result") == 1) {
						ToastUtils
								.showShort(AddressAddActivity.this, "添加地址成功！");
						AddressAddActivity.this.setResult(RESULT_OK);
						AddressAddActivity.this.finish();
					} else {
						ToastUtils
								.showShort(AddressAddActivity.this, "添加地址失败！");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Throwable error, String response) {
				// TODO Auto-generated method stub
				super.onFailure(error, response);
			}

			@Override
			public void onFailure(Throwable error) {
				// TODO Auto-generated method stub
				super.onFailure(error);
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.postAddress(id, reciver, tel, zipcode, area, detail, res);
	}
}
