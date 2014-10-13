package com.wukong.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wukong.R;
import com.wukong.data.AddressModel;
import com.wukong.login.LoginActivity;
import com.wukong.my.MyAddressActivity;
import com.wukong.my.MyDataActivity;
import com.wukong.my.MyOrderActivity;
import com.wukong.my.MyRouteActivity;
import com.wukong.utils.WKHttpClient;

public class MyFragment extends Fragment implements OnClickListener {
	// 主要用来判定程序被杀死后Fragment重新启动后不受activity控制的问题，归根在于解决界面重叠的问题
	private boolean isMy = false;
	private TextView headbar_txt;// 头部标题内容
	/******** 头部左边布局 ********/
	private RelativeLayout headbar_left_layout;// 左边父控件
	private ImageView headbar_left_image;// 存放左边图片控件
	private TextView headbar_left_txt;// 编辑左边内容控件
	/******** 头部右面布局 ********/
	// private RelativeLayout headbar_right_layout;// 右边父控件
	// private ImageView headbar_right_image;// 存放右边图片控件
	// private TextView headbar_right_txt;// 编辑右边内容控件

	/******** 我的信息栏 ********/
	private ImageView myhead;// 头像
	private TextView name;// 姓名
	private TextView balance;// 余额
	/******** 我的帮助栏 ********/
	private RelativeLayout my_order;// 我的订单
	private RelativeLayout my_route;// 我的路线
	private RelativeLayout my_data;// 我的资料
	private RelativeLayout my_Recharge;// 充值提现
	private RelativeLayout my_wukonger;// 化身悟空
	private RelativeLayout my_address;// 常用地址
	private RelativeLayout my_share;// 分享
	private String id;

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
			isMy = true;
		}
		// 决定Fragment是否弹出自己相关的菜单按钮
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// 添加首页布局文件
		View view = inflater.inflate(R.layout.fragment_my, container, false);
		id = LoginActivity.id;
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
		myhead = (ImageView) view.findViewById(R.id.my_headview);
		name = (TextView) view.findViewById(R.id.my_name);
		balance = (TextView) view.findViewById(R.id.my_balance);
		my_order = (RelativeLayout) view.findViewById(R.id.my_order);
		my_route = (RelativeLayout) view.findViewById(R.id.my_route);
		my_data = (RelativeLayout) view.findViewById(R.id.my_data);
		my_Recharge = (RelativeLayout) view.findViewById(R.id.my_Recharge);
		my_wukonger = (RelativeLayout) view.findViewById(R.id.my_wukonger);
		my_address = (RelativeLayout) view.findViewById(R.id.my_address);
		my_share = (RelativeLayout) view.findViewById(R.id.my_share);
		/******* 改变标题栏 *******/
		headbar_txt.setText("个人中心");
		headbar_left_layout.setVisibility(View.VISIBLE);
		headbar_left_image.setBackgroundResource(R.drawable.actionbar_gprs);
		headbar_left_txt.setText("北京");
		// headbar_right_layout.setVisibility(View.VISIBLE);
		// headbar_right_image.setVisibility(View.GONE);
		// headbar_right_txt.setText("编辑");
		/******* 点击事件 *******/
		// headbar_right_layout.setOnClickListener(this);
		my_order.setOnClickListener(this);
		my_route.setOnClickListener(this);
		my_data.setOnClickListener(this);
		my_Recharge.setOnClickListener(this);
		my_wukonger.setOnClickListener(this);
		my_address.setOnClickListener(this);
		my_share.setOnClickListener(this);
		GetUser(id);
		return view;

	}

	private void GetUser(String id) {
		// TODO Auto-generated method stub
		AsyncHttpResponseHandler res = new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				// TODO Auto-generated method stub
				Log.i("TAG", "返回:" + response);
				parseUser(response);
			}
		};
		WKHttpClient client = new WKHttpClient();
		client.userdetail(id, res);
	}

	protected void parseUser(String response) {
		// TODO Auto-generated method stub
		JSONObject result;
		try {
			result = new JSONObject(response);
			// JSONObject obj = result.getJSONObject("addlist");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		// case R.id.headbar_right_layout:// 头部右键编辑点击事件
		// break;
		case R.id.my_order:// 我的订单点击事件
			Intent orderintent = new Intent(getActivity(),
					MyOrderActivity.class);
			startActivity(orderintent);
			break;
		case R.id.my_route:// 我的路线点击事件
			Intent routeintent = new Intent(getActivity(),
					MyRouteActivity.class);
			startActivity(routeintent);
			break;
		case R.id.my_data:// 我的资料点击事件
			Intent dataintent = new Intent(getActivity(), MyDataActivity.class);
			startActivity(dataintent);
			break;
		case R.id.my_Recharge:// 我的充值提现点击事件
			break;
		case R.id.my_wukonger:// 化身悟空点击事件
			break;
		case R.id.my_address:// 常用地址点击事件
			Intent addressintent = new Intent(getActivity(),
					MyAddressActivity.class);
			startActivity(addressintent);
			break;
		case R.id.my_share:// 分享点击事件
			break;

		default:
			break;
		}
	}
}
