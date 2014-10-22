package com.wukong.main;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.WKApplication;
import com.wukong.bean.OrderBean;
import com.wukong.data.AddressModel;
import com.wukong.data.GoodsModel;
import com.wukong.my.MyAddressActivity;
import com.wukong.support.debug.AppLog;
import com.wukong.support.notice.HandlerJson;
import com.wukong.support.notice.NoticeUtils;
import com.wukong.utils.Constants;
import com.wukong.utils.ToastUtils;
import com.wukong.utils.WKHttpClient;

public class SendActivity extends Activity implements OnClickListener {
	/************* 标题栏 **************/
	private static final String TAG = SendActivity.class.getSimpleName();
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
	private EditText price;// 价值
	private RelativeLayout info_layout;
	private EditText info;// 备注信息
	/************* 发货人 **************/
	private EditText sendname;// 发货人姓名
	private EditText sendtel;// 发货人电话
	private RelativeLayout sendaddress_layout;
	private EditText sendaddress;// 发货人地址
	/************* 收货人 **************/
	private EditText recivename;// 收货人姓名
	private EditText recivetel;// 收货人电话
	private RelativeLayout reciveaddress_layout;
	private EditText reciveaddress;// 收货人地址

	private Button submit;// 提交

	private GoodsModel goodsmodel;// 商品数据
	private Context context;
	private final int START_ADDRESS = 3;
	private final int END_ADDRESS = 4;
	private String tag = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mysend_activity);
		context = this;
		TitleView();
		InitView();
	}

	private void TitleView() {
		// TODO Auto-generated method stub
		tag = getIntent().getStringExtra("tag");
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		if (tag.equals("e1")) {
			head_txt.setText("同城发货");
		} else {
			head_txt.setText("异地发货");
		}

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
		price = (EditText) findViewById(R.id.goods_price);
		info_layout = (RelativeLayout) findViewById(R.id.mysend_info);
		info = (EditText) findViewById(R.id.goods_info);
		sendname = (EditText) findViewById(R.id.send_name);
		sendtel = (EditText) findViewById(R.id.send_tel);
		sendaddress_layout = (RelativeLayout) findViewById(R.id.mysend_sendaddress);
		sendaddress = (EditText) findViewById(R.id.send_address);
		recivename = (EditText) findViewById(R.id.receive_name);
		recivetel = (EditText) findViewById(R.id.receive_tel);
		reciveaddress_layout = (RelativeLayout) findViewById(R.id.mysend_receiveaddress);
		reciveaddress = (EditText) findViewById(R.id.receive_address);
		submit = (Button) findViewById(R.id.mysend_submit);

		type_layout.setOnClickListener(this);
		price_layout.setOnClickListener(this);
		info_layout.setOnClickListener(this);
		sendaddress_layout.setOnClickListener(this);
		reciveaddress_layout.setOnClickListener(this);
		submit.setOnClickListener(this);

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
				this.type.setText(type);
			}
		} else if (requestCode == START_ADDRESS) {
			AddressModel addressModel = (AddressModel) data
					.getSerializableExtra("address");
			sendaddress.setText(addressModel.getDetail());
		} else if (requestCode == END_ADDRESS) {
			AddressModel addressModel = (AddressModel) data
					.getSerializableExtra("address");
			reciveaddress.setText(addressModel.getDetail());
		}

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
			// Intent sendaddressintent = new Intent(SendActivity.this,
			// SendAddressActivity.class);
			// startActivityForResult(sendaddressintent,
			// Constants.START_ACTIVITY.SEND_TO_SENDADDRESS);
			getAddress(START_ADDRESS);
			break;
		case R.id.mysend_receiveaddress:// 收货地址
			// Intent receiveaddressintent = new Intent(SendActivity.this,
			// ReceiveAddressActivity.class);
			// startActivityForResult(receiveaddressintent,
			// Constants.START_ACTIVITY.SEND_TO_RECEIVEADDRESS);
			getAddress(END_ADDRESS);
			break;
		case R.id.mysend_submit:
			publishGoods();
			break;
		default:
			break;
		}
	}

	private void publishGoods() {
		OrderBean orderBean = new OrderBean();
		orderBean.setUid(WKApplication.getInstance().getPersonInfoBean()
				.getId());
		ToastUtils.showLong(context, "ID:"
				+ WKApplication.getInstance().getPersonInfoBean().getId());
		orderBean.setTag(tag);
		String category = type.getText().toString();
		if (TextUtils.isEmpty(category)) {
			type_layout.startAnimation(AnimationUtils.loadAnimation(context,
					R.anim.shake));
			ToastUtils.showShort(context, getString(R.string.category_notice));
			return;
		}
		orderBean.setCategory(category);
		String gname = name.getText().toString();
		if (TextUtils.isEmpty(gname)) {
			findViewById(R.id.mysend_name).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, getString(R.string.gname_notice));
			return;
		}
		orderBean.setGname(gname);
		String weight = this.weight.getText().toString();
		if (TextUtils.isEmpty(weight)) {
			findViewById(R.id.mysend_weight).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, getString(R.string.weight_notice));
			return;
		}
		orderBean.setWeight(weight);
		String cost = price.getText().toString();
		if (TextUtils.isEmpty(cost)) {
			findViewById(R.id.mysend_price).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, getString(R.string.gname_notice));
			return;
		}
		orderBean.setCost(cost);
		String remarks = info.getText().toString();
		orderBean.setRemarks(remarks);
		String shipper = sendname.getText().toString();
		if (TextUtils.isEmpty(shipper)) {
			findViewById(R.id.mysend_sendname).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, getString(R.string.sender_notice));
			return;
		}
		orderBean.setShipper(shipper);
		String s_tel = sendtel.getText().toString();
		if (TextUtils.isEmpty(s_tel)) {
			findViewById(R.id.mysend_sendtel).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context,
					getString(R.string.sender_phone_notice));
			return;
		}
		orderBean.setS_tel(s_tel);
		String s_address = sendaddress.getText().toString();
		if (TextUtils.isEmpty(s_address)) {
			findViewById(R.id.mysend_sendaddress).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context,
					getString(R.string.sender_address_notice));
			return;
		}
		orderBean.setS_address(s_address);
		String receiver = recivename.getText().toString();
		if (TextUtils.isEmpty(receiver)) {
			findViewById(R.id.mysend_receivename).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context, getString(R.string.receiver_notice));
			return;
		}
		orderBean.setReceiver(receiver);
		String r_tel = recivetel.getText().toString();
		if (TextUtils.isEmpty(r_tel)) {
			findViewById(R.id.mysend_receivetel).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context,
					getString(R.string.receiver_phone_notice));
			return;
		}
		orderBean.setR_tel(r_tel);
		String r_address = reciveaddress.getText().toString();
		if (TextUtils.isEmpty(r_tel)) {
			findViewById(R.id.mysend_receiveaddress).startAnimation(
					AnimationUtils.loadAnimation(context, R.anim.shake));
			ToastUtils.showShort(context,
					getString(R.string.receiver_phone_notice));
			return;
		}
		orderBean.setR_address(r_address);
		WKHttpClient.postGoods(orderBean, new HandlerJson(SendActivity.this,
				getString(R.string.publish_goods), 1) {

			@Override
			public void parseJson(JSONObject response) {
				// TODO Auto-generated method stub
				AppLog.i(TAG, "快件返回:" + response);
				NoticeUtils.showSuccessfulNotification(context);
			}
		});
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

}
