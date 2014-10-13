package com.wukong.my;

import com.wukong.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyDataActivity extends Activity implements OnClickListener {
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private RelativeLayout data_head_layout;
	private RelativeLayout data_sex_layout;
	private RelativeLayout data_age_layout;
	private ImageView submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mydata_activity);
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		submit=(ImageView)findViewById(R.id.headbar_right_btn);
		submit.setImageResource(R.drawable.submit_sec);
		findViewById(R.id.headbar_right_layout).setVisibility(View.VISIBLE);
		head_txt.setText("我的资料");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		left_layout.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		case R.id.headbar_right_btn:
			
			break;
		default:
			break;
		}
	}

}
