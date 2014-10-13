package com.wukong.my;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.adapter.OrderAdapter;
import com.wukong.fragment.OrderFragment;

public class MyOrderActivity extends Activity implements OnClickListener,
		OnItemClickListener {
	private TextView head_txt;// 标题头内容
	private RelativeLayout left_layout;// 标题头左键
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private Button order_left;// 订单栏左键
	private Button order_right;// 订单栏右键
	private ListView orderlist;// 展示订单列表容器
	private OrderAdapter orderadapter;// 订单列表适配器

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myorder_activity);
		head_txt = (TextView) findViewById(R.id.headbar_txt);
		left_layout = (RelativeLayout) findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) findViewById(R.id.headbar_left_txt);
		order_left = (Button) findViewById(R.id.myorder_left_btn);
		order_right = (Button) findViewById(R.id.myorder_right_btn);
		orderlist = (ListView) findViewById(R.id.myorder_listview);
		head_txt.setText("我的订单");
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_return);
		headbar_left_txt.setText("返回");
		left_layout.setVisibility(View.VISIBLE);
		// 文本显示为灰色
		ColorStateList corlorGray = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.gray);
		// 文本显示为白色
		ColorStateList corlorWhite = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.white);
		order_left.setBackgroundResource(R.drawable.order_btn1);
		order_left.setTextColor(corlorGray);
		order_right.setBackgroundResource(R.drawable.order_btn2);
		order_right.setTextColor(corlorWhite);
		/********* 点击事件 *********/
		order_left.setOnClickListener(this);
		order_right.setOnClickListener(this);
		left_layout.setOnClickListener(this);
		/********* 订单列表 *********/
		orderadapter = new OrderAdapter(MyOrderActivity.this);
		orderlist.setAdapter(orderadapter);
		orderlist.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// 文本显示为灰色
		ColorStateList corlorGray = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.gray);
		// 文本显示为白色
		ColorStateList corlorWhite = MyOrderActivity.this.getResources()
				.getColorStateList(R.color.white);
		switch (arg0.getId()) {
		case R.id.headbar_left_layout:// 返回键
			finish();
			break;
		case R.id.myorder_left_btn:// 订单栏左键点击事件
			order_left.setBackgroundResource(R.drawable.order_btn1);
			order_left.setTextColor(corlorGray);
			order_right.setBackgroundResource(R.drawable.order_btn2);
			order_right.setTextColor(corlorWhite);
			break;
		case R.id.myorder_right_btn:// 订单栏右键点击事件
			order_left.setBackgroundResource(R.drawable.order_btn1_pressed);
			order_left.setTextColor(corlorWhite);
			order_right.setBackgroundResource(R.drawable.order_btn2_pressed);
			order_right.setTextColor(corlorGray);
			break;
		default:
			break;
		}
	}

	/**
	 * 这里主要处理跳转到每条订单的详情页
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
