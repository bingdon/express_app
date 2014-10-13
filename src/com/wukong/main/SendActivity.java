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
	/************* ������ **************/
	private TextView head_txt;// ����ͷ����
	private RelativeLayout left_layout;// ����ͷ���
	private ImageView headbar_left_image;// ������ͼƬ�ؼ�
	private TextView headbar_left_txt;// �༭������ݿؼ�
	/************* ��Ʒ��Ϣ **************/
	private RelativeLayout type_layout;
	private TextView type;// ����
	private EditText name;// ����
	private EditText weight;// ����
	private RelativeLayout price_layout;
	private TextView price;// ��ֵ
	private RelativeLayout info_layout;
	private TextView info;// ��ע��Ϣ
	/************* ������ **************/
	private EditText sendname;// ����������
	private EditText sendtel;// �����˵绰
	private RelativeLayout sendaddress_layout;
	private TextView sendaddress;// �����˵�ַ
	/************* �ջ��� **************/
	private EditText recivename;// �ջ�������
	private EditText recivetel;// �ջ��˵绰
	private RelativeLayout reciveaddress_layout;
	private TextView reciveaddress;// �ջ��˵�ַ

	private Button submit;// �ύ

	private GoodsModel goodsmodel;// ��Ʒ����

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
		head_txt.setText("��Ҫ����");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("����");
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
		case R.id.headbar_left_layout:// ���ؼ�
			finish();
			break;
		case R.id.mysend_type:// ����
			Intent typeintent = new Intent(SendActivity.this,
					GoodsTypeActivity.class);
			startActivityForResult(typeintent,
					Constants.START_ACTIVITY.SEND_TO_TYPE);
			break;
		case R.id.mysend_price:// ��ֵ

			break;
		case R.id.mysend_info:// ��Ϣ

			break;
		case R.id.mysend_sendaddress:// ������ַ
//			Intent sendaddressintent = new Intent(SendActivity.this,
//					SendAddressActivity.class);
//			startActivityForResult(sendaddressintent,
//					Constants.START_ACTIVITY.SEND_TO_SENDADDRESS);
			break;
		case R.id.mysend_receiveaddress:// �ջ���ַ
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
