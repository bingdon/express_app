package com.wukong.my;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.R;
import com.wukong.adapter.MyAddressAdapter;
import com.wukong.data.AddressModel;
import com.wukong.login.LoginActivity;
import com.wukong.utils.Constants;
import com.wukong.utils.WKHttpClient;

public class MyAddressActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private TextView headbar_txt;// 头部标题内容
	/******** 头部左边布局 ********/
	private RelativeLayout headbar_left_layout;// 左边父控件
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	/******** 头部右面布局 ********/
	private RelativeLayout headbar_right_layout;// 右边父控件
	private ImageView headbar_right_image;// 存放右边图片控件
	private TextView headbar_right_txt;// 编辑右边内容控件
	private ListView addresslist;
	private MyAddressAdapter addressadapter;
	private String id;
	private ArrayList<AddressModel> addressItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myaddress_activity);
		addressItem = new ArrayList<AddressModel>();
		id = LoginActivity.id;
		getAddress(id);
	}

	private void getAddress(String id) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				Log.i("TAG", "返回:" + response);
				parseAlladdress(response);
				InitView();
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.usedAddress(id, res);
	}

	protected void parseAlladdress(String response) {
		JSONObject result;
		try {
			result = new JSONObject(response);
			JSONObject obj = result.getJSONObject("addlist");
			JSONArray json = obj.getJSONArray("resultlist");
			int length = json.length();
			for (int i = 0; i < length; i++) {
				AddressModel item = new AddressModel();
				obj = json.getJSONObject(i);
				item.setId(obj.getString("id"));
				item.setReciver(obj.getString("reciver"));
				item.setTel(obj.getString("tel"));
				item.setZipcode(obj.getString("zipcode"));
				item.setArea(obj.getString("area"));
				item.setDetail(obj.getString("detail"));
				item.isChecked();
				addressItem.add(item);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void InitView() {
		// TODO Auto-generated method stub
		headbar_txt = (TextView) findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		headbar_right_layout = (RelativeLayout) findViewById(R.id.headbar_right_layout);
		headbar_right_image = (ImageView) findViewById(R.id.headbar_right_btn);
		headbar_right_txt = (TextView) findViewById(R.id.headbar_right_txt);
		addresslist = (ListView) findViewById(R.id.myaddress_listview);
		/******* 改变标题栏 *******/
		headbar_txt.setText("常用地址");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		headbar_right_layout.setVisibility(View.VISIBLE);
		headbar_right_image
				.setBackgroundResource(R.drawable.myaddress_right_selector);
		headbar_right_txt.setVisibility(View.GONE);
		headbar_left_layout.setOnClickListener(this);
		headbar_right_layout.setOnClickListener(this);
		addressadapter = new MyAddressAdapter(MyAddressActivity.this,
				addressItem);
		addresslist.setAdapter(addressadapter);
		addresslist.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		case R.id.headbar_right_layout:// 添加键
			Intent intent = new Intent(MyAddressActivity.this,
					AddressAddActivity.class);
			startActivityForResult(intent,
					Constants.START_ACTIVITY.ADDRESS_TO_ADD_ADDRESS);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MyAddressActivity.this,
				AddressDetailActivity.class);
		intent.putExtra("id", addressItem.get(arg2).getId());
		startActivityForResult(intent,
				Constants.START_ACTIVITY.ADDRESS_TO_ADDRESSDETAIL);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case Constants.START_ACTIVITY.ADDRESS_TO_ADD_ADDRESS:
				addressItem.clear();
				addressadapter.notifyDataSetChanged();
				getAddress(id);
				break;
			case Constants.START_ACTIVITY.ADDRESS_TO_ADDRESSDETAIL:
				addressItem.clear();
				addressadapter.notifyDataSetChanged();
				getAddress(id);
				break;
			}
		}
	}
}
