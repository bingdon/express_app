package com.wukong.main;

import com.wukong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GetOrderSuccessActivity extends Activity implements
		ActivityInitInterface, OnClickListener {

	private TextView getOrderTextView;
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_getorder_success);
		initView();
		initData();
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		getOrderTextView = (TextView) findViewById(R.id.getorder_txt);
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		head_txt.setText(R.string.get_order_success);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText(R.string.back);
		left_layout.setOnClickListener(this);
		left_layout.setVisibility(View.VISIBLE);
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		String tel = getIntent().getStringExtra("tel");
		getOrderTextView.append(tel + getString(R.string.get_order_m1));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
			
		default:
			break;
		}
	}

}
