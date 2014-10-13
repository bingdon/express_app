package com.wukong.more;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;

public class ContactActivity extends Activity implements OnClickListener {
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private TextView phone;// 电话
	private TextView ucode;// 邮编
	private TextView email;// 电子邮箱
	private TextView qq;// qq
	private TextView address;// 地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_activity);
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		phone = (TextView) findViewById(R.id.contact_phone);
		ucode = (TextView) findViewById(R.id.contact_ucode);
		email = (TextView) findViewById(R.id.contact_email);
		qq = (TextView) findViewById(R.id.contact_qq);
		address = (TextView) findViewById(R.id.contact_address);
		head_txt.setText("联系悟空帮递");
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

}
