package com.wukong.fragment;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.adapter.OrderAdapter;

public class OrderFragment extends Fragment implements OnClickListener,
		OnItemClickListener {
	// 主要用来判定程序被杀死后Fragment重新启动后不受activity控制的问题，归根在于解决界面重叠的问题
	private boolean isOrder = false;
	private TextView headbar_txt;// 头部标题内容
	/******** 头部左边布局 ********/
	private RelativeLayout headbar_left_layout;// 左边父控件
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	private Button order_left;// 订单栏左键
	private Button order_right;// 订单栏右键
	private TextView order_total;// 订单总数
	private TextView order_miss;// 订单爽约数
	private ListView orderlist;// 展示订单列表容器
	private OrderAdapter orderadapter;// 订单列表适配器

	/**
	 * 检查绑定的Activity是否实现了接口
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			isOrder = true;
		}
		// 决定Fragment是否弹出自己相关的菜单按钮
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 添加首页布局文件
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		/******* 初始化控件 *******/
		headbar_txt = (TextView) view.findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) view
				.findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) view
				.findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) view.findViewById(R.id.headbar_left_txt);
		order_left = (Button) view.findViewById(R.id.order_left_btn);
		order_right = (Button) view.findViewById(R.id.order_right_btn);
		order_total = (TextView) view.findViewById(R.id.order_total);
		order_miss = (TextView) view.findViewById(R.id.order_miss);
		orderlist = (ListView) view.findViewById(R.id.order_listview);
		/******* 改变标题栏 *******/
		headbar_txt.setText("我的订单");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("北京");
		/********* 初始化订单栏 *********/
		// 文本显示为灰色
		ColorStateList corlorGray = OrderFragment.this.getResources()
				.getColorStateList(R.color.gray);
		// 文本显示为白色
		ColorStateList corlorWhite = OrderFragment.this.getResources()
				.getColorStateList(R.color.white);
		order_left.setBackgroundResource(R.drawable.order_btn1);
		order_left.setTextColor(corlorGray);
		order_right.setBackgroundResource(R.drawable.order_btn2);
		order_right.setTextColor(corlorWhite);
		/********* 订单列表 *********/
		orderadapter = new OrderAdapter(getActivity());
		orderlist.setAdapter(orderadapter);
		orderlist.setOnItemClickListener(this);
		/********* 点击事件 *********/
		order_left.setOnClickListener(this);
		order_right.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// 文本显示为灰色
		ColorStateList corlorGray = OrderFragment.this.getResources()
				.getColorStateList(R.color.gray);
		// 文本显示为白色
		ColorStateList corlorWhite = OrderFragment.this.getResources()
				.getColorStateList(R.color.white);
		switch (arg0.getId()) {
		case R.id.order_left_btn:// 订单栏左键点击事件
			order_left.setBackgroundResource(R.drawable.order_btn1);
			order_left.setTextColor(corlorGray);
			order_right.setBackgroundResource(R.drawable.order_btn2);
			order_right.setTextColor(corlorWhite);
			break;
		case R.id.order_right_btn:// 订单栏右键点击事件
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
