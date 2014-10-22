package com.wukong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wukong.R;
import com.wukong.main.RouteActivity;
import com.wukong.main.SendActivity;

public class MainFragment extends Fragment implements OnClickListener {
	// 主要用来判定程序被杀死后Fragment重新启动后不受activity控制的问题，归根在于解决界面重叠的问题
	private boolean isMain = false;
	private TextView headbar_txt;// 头部标题内容
	/******** 头部左边布局 ********/
	private RelativeLayout headbar_left_layout;// 左边父控件
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	/******** 头部右面布局 ********/
	// private RelativeLayout headbar_right_layout;// 右边父控件
	// private ImageView headbar_right_image;// 存放右边图片控件
	// private TextView headbar_right_txt;// 编辑右边内容控件
	private LinearLayout city_wide_layout;// 同城发件
	private LinearLayout different_places_layout;// 异地发件
	private LinearLayout long_distance_route_layout;// 长途线路
	private LinearLayout city_route_layout;// 同城线路

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
			isMain = true;
		}
		// 决定Fragment是否弹出自己相关的菜单按钮
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 添加首页布局文件
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		/******* 初始化控件 *******/
		headbar_txt = (TextView) view.findViewById(R.id.headbar_txt);
		headbar_left_layout = (RelativeLayout) view
				.findViewById(R.id.headbar_left_layout);
		headbar_left_image = (ImageView) view
				.findViewById(R.id.headbar_left_btn);
		headbar_left_txt = (TextView) view.findViewById(R.id.headbar_left_txt);
		// headbar_right_layout = (RelativeLayout) view
		// .findViewById(R.id.headbar_right_layout);
		// headbar_right_image = (ImageView) view
		// .findViewById(R.id.headbar_right_btn);
		// headbar_right_txt = (TextView) view
		// .findViewById(R.id.headbar_right_txt);
		city_wide_layout = (LinearLayout) view
				.findViewById(R.id.city_wide_layout);
		different_places_layout = (LinearLayout) view
				.findViewById(R.id.different_places_layout);
		long_distance_route_layout = (LinearLayout) view
				.findViewById(R.id.long_distance_route_layout);
		city_route_layout = (LinearLayout) view
				.findViewById(R.id.city_route_layout);
		/******* 改变标题栏 *******/
		headbar_txt.setText("悟空快递");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("北京");
		// headbar_right_layout.setVisibility(View.VISIBLE);
		// headbar_right_image.setVisibility(View.GONE);
		// headbar_right_txt.setText("更多");
		/******* 点击事件 *******/
		headbar_left_layout.setOnClickListener(this);
		// headbar_right_layout.setOnClickListener(this);
		city_wide_layout.setOnClickListener(this);
		different_places_layout.setOnClickListener(this);
		long_distance_route_layout.setOnClickListener(this);
		city_route_layout.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.city_wide_layout:// 同城发件点击事件
			Intent intent1 = new Intent(getActivity(), SendActivity.class);
			intent1.putExtra("tag", "e1");
			startActivity(intent1);
			break;
		case R.id.different_places_layout:// 异地发件点击事件
			Intent intent = new Intent(getActivity(), SendActivity.class);
			intent.putExtra("tag", "e2");
			startActivity(intent);
			break;
		case R.id.long_distance_route_layout:// 长途线路点击事件
			Intent routeintent1 = new Intent(getActivity(), RouteActivity.class);
			routeintent1.putExtra("tag", "e2");
			startActivity(routeintent1);
			break;
		case R.id.city_route_layout:// 同城线路点击事件
			Intent routeintent2 = new Intent(getActivity(), RouteActivity.class);
			routeintent2.putExtra("tag", "e1");
			startActivity(routeintent2);
			break;

		default:
			break;
		}
	}

}
