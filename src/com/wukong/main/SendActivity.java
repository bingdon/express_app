package com.wukong.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.data.GoodsModel;
import com.wukong.utils.Constants;

public class SendActivity extends Activity implements OnClickListener {
	/************* 标题栏 **************/
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	/************* 商品信息 **************/
	private RelativeLayout type_layout;
	private TextView type;// 类型
	private EditText name;// 名称
	private EditText weight;// 重量
	private RelativeLayout price_layout;
	private TextView price;// 价值
	private RelativeLayout info_layout;
	private TextView info;// 备注信息
	/************* 发货人 **************/
	private EditText sendname;// 发货人姓名
	private EditText sendtel;// 发货人电话
	private RelativeLayout sendaddress_layout;
	private TextView sendaddress;// 发货人地址
	/************* 收货人 **************/
	private EditText recivename;// 收货人姓名
	private EditText recivetel;// 收货人电话
	private RelativeLayout reciveaddress_layout;
	private TextView reciveaddress;// 收货人地址

	private Button submit;// 提交

	private GoodsModel goodsmodel;// 商品数据

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mysend_activity);
		TitleView();
		InitView();
	}

	private void TitleView() {
		// TODO Auto-generated method stub
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		head_txt.setText("我要发货");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
	}

	private void InitView() {
		// TODO Auto-generated method stub
		type_layout = (RelativeLayout) findViewById(R.id.mysend_type);
		type = (TextView) findViewById(R.id.goods_type);
		name = (EditText) findViewById(R.id.goods_name);
		weight = (EditText) findViewById(R.id.goods_weights);
		price_layout = (RelativeLayout) findViewById(R.id.mysend_price);
		price = (TextView) findViewById(R.id.goods_price);
		info_layout = (RelativeLayout) findViewById(R.id.mysend_info);
		info = (TextView) findViewById(R.id.goods_info);
		sendname = (EditText) findViewById(R.id.send_name);
		sendtel = (EditText) findViewById(R.id.send_tel);
		sendaddress_layout = (RelativeLayout) findViewById(R.id.mysend_sendaddress);
		sendaddress = (TextView) findViewById(R.id.send_address);
		recivename = (EditText) findViewById(R.id.receive_name);
		recivetel = (EditText) findViewById(R.id.receive_tel);
		reciveaddress_layout = (RelativeLayout) findViewById(R.id.mysend_receiveaddress);
		reciveaddress = (TextView) findViewById(R.id.receive_address);
		submit = (Button) findViewById(R.id.mysend_submit);

		type_layout.setOnClickListener(this);
		price_layout.setOnClickListener(this);
		info_layout.setOnClickListener(this);
		sendaddress_layout.setOnClickListener(this);
		reciveaddress_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		case R.id.mysend_type:// 类型
			Intent typeintent = new Intent(SendActivity.this,
					GoodsTypeActivity.class);
			startActivityForResult(typeintent,
					Constants.START_ACTIVITY.SEND_TO_TYPE);
			break;
		case R.id.mysend_price:// 价值

			break;
		case R.id.mysend_info:// 信息

			break;
		case R.id.mysend_sendaddress:// 发货地址
//			Intent sendaddressintent = new Intent(SendActivity.this,
//					SendAddressActivity.class);
//			startActivityForResult(sendaddressintent,
//					Constants.START_ACTIVITY.SEND_TO_SENDADDRESS);
			break;
		case R.id.mysend_receiveaddress:// 收货地址
			// Intent receiveaddressintent = new Intent(SendActivity.this,
			// ReceiveAddressActivity.class);
			// startActivityForResult(receiveaddressintent,
			// Constants.START_ACTIVITY.SEND_TO_RECEIVEADDRESS);
			break;
		default:
			break;
		}
	}
}
