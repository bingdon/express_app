package com.wukong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

import com.wukong.fragment.MainFragment;
import com.wukong.fragment.MoreFragment;
import com.wukong.fragment.MyFragment;
import com.wukong.fragment.NearFragment;
import com.wukong.fragment.OrderFragment;
import com.wukong.utils.Constants;

/**
 * @ClassName: CheifActivity
 * @Description:应用程序的主界面，各个子界面采用了Fragment来实现了
 * @author zc
 * @date
 * 
 */
public class CheifActivity extends FragmentActivity implements OnClickListener {
	private ImageView bottom_main;// 首页菜单
	private ImageView bottom_order;// 订单菜单
	private ImageView bottom_my;// 我的菜单
	private ImageView bottom_near;// 附近菜单
	private ImageView bottom_more;// 更多菜单
	private MainFragment mainfragment;// 首页界面
	private OrderFragment orderfragment;// 订单界面
	private MyFragment myfragment;// 我的界面
	private NearFragment nearfragment;// 附近界面
	private MoreFragment morefragment;// 更多界面
	// 管理fragment
	private FragmentManager fragmentManager = null;
	// 当前界面
	private Fragment currentFragment = null;
	// 控制当前界面的标志
	private int current = 0;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.cheif_activity);
		// 初始化底部导航栏
		initNavigatioLayout();
		// 首先显示首页界面
		fragmentManager = this.getSupportFragmentManager();
		if (arg0 != null) {
			current = arg0.getInt("current", -1);
		} else {
			current = -1;
		}
		/************ 当前界面是首页界面 ***********/
		if (current <= 0) {
			mainfragment = new MainFragment();
			currentFragment = mainfragment;
			fragmentManager.beginTransaction()
					.add(R.id.chief_content_frame, currentFragment).commit();
			current = Constants.FRAGMENT_TYPE.MAIN;
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1_pressed);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
		} else if (current == Constants.FRAGMENT_TYPE.MAIN) {
			if (bottom_main != null) {
				switchBottomCorlor(bottom_main);
			}
			mainfragment = new MainFragment();// 实例化
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, mainfragment).commit();
			currentFragment = mainfragment;// 当前界面为消息
			current = Constants.FRAGMENT_TYPE.MAIN;// 当前界面标志为Constants.FRAGMENT_TYPE.MESSAGE
			/************ 当前界面是订单界面 ***********/
		} else if (current == Constants.FRAGMENT_TYPE.ORDER) {
			if (bottom_order != null) {
				switchBottomCorlor(bottom_order);
			}
			orderfragment = new OrderFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, orderfragment).commit();
			// showTabContent(orderFragment);
			currentFragment = orderfragment;
			current = Constants.FRAGMENT_TYPE.ORDER;
			/************ 当前界面是我的界面 ***********/
		} else if (current == Constants.FRAGMENT_TYPE.MY) {
			if (bottom_my != null) {
				switchBottomCorlor(bottom_my);
			}
			myfragment = new MyFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, myfragment).commit();
			currentFragment = myfragment;
			current = Constants.FRAGMENT_TYPE.MY;
			/************ 当前界面是附近界面 ***********/
		} else if (current == Constants.FRAGMENT_TYPE.NEAR) {
			if (bottom_near != null) {
				switchBottomCorlor(bottom_near);
			}
			nearfragment = new NearFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, nearfragment).commit();
			currentFragment = nearfragment;
			current = Constants.FRAGMENT_TYPE.NEAR;
			/************ 当前界面是个人中心界面 ***********/
		} else if (current == Constants.FRAGMENT_TYPE.MORE) {
			if (bottom_more != null) {
				switchBottomCorlor(bottom_more);
			}
			morefragment = new MoreFragment();
			fragmentManager.beginTransaction()
					.replace(R.id.chief_content_frame, morefragment).commit();
			currentFragment = morefragment;
			current = Constants.FRAGMENT_TYPE.MORE;
		}
	}

	/********** 利用onSaveInstanceState()方法保存Activity状态 ***********/
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putInt("current", current);// 保存当前切换的状态
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// Log.d(TAG, "this cheif is onPause");
	}

	/***
	 * 
	 * 功能说明： 首先进行的是底部导航栏的初始化
	 * 
	 * @Title: initNavigatioLayout
	 * @Description: 初始化底部导航栏
	 * @return void 返回类型
	 * @exception
	 * @throws
	 */
	private void initNavigatioLayout() {
		// TODO Auto-generated method stub
		bottom_main = (ImageView) findViewById(R.id.app_bottom_main);
		bottom_order = (ImageView) findViewById(R.id.app_bottom_order);
		bottom_my = (ImageView) findViewById(R.id.app_bottom_my);
		bottom_near = (ImageView) findViewById(R.id.app_bottom_near);
		bottom_more = (ImageView) findViewById(R.id.app_bottom_more);
		bottom_main.setOnClickListener(this);
		bottom_order.setOnClickListener(this);
		bottom_my.setOnClickListener(this);
		bottom_near.setOnClickListener(this);
		bottom_more.setOnClickListener(this);
	}

	/**
	 * 底部导航栏的点击事件
	 * 
	 * @param v
	 */
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.app_bottom_main:// 首页点击事件
			if (!(currentFragment instanceof MainFragment)) {// 如果当前不是首页页面
				/*********** 如果当前是首页页面 ***********/
				mainfragment = new MainFragment();// 实例化首页页面
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, mainfragment)
						.commit();// 变化之间导航，提交事务
				currentFragment = mainfragment;// 当前界面为首页
				current = Constants.FRAGMENT_TYPE.MAIN;// 当前界面标志为Constants.FRAGMENT_TYPE.MAIN
			}
			// Log.d(TAG, "The click indexnavigationIcon = " + v.getId());
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_order:// 订单点击事件
			if (!(currentFragment instanceof OrderFragment)) {
				orderfragment = new OrderFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, orderfragment)
						.commit();
				// showTabContent(orderFragment);
				currentFragment = orderfragment;
				current = Constants.FRAGMENT_TYPE.ORDER;
			}
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_my:// 我的点击事件
			if (!(currentFragment instanceof MyFragment)) {
				myfragment = new MyFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, myfragment).commit();
				// showTabContent(orderFragment);
				currentFragment = myfragment;
				current = Constants.FRAGMENT_TYPE.MY;
			}
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_near:// 附近点击事件
			if (!(currentFragment instanceof NearFragment)) {
				nearfragment = new NearFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, nearfragment)
						.commit();
				// showTabContent(orderFragment);
				currentFragment = nearfragment;
				current = Constants.FRAGMENT_TYPE.NEAR;
			}
			switchBottomCorlor(arg0);
			break;
		case R.id.app_bottom_more:// 更多点击事件
			if (!(currentFragment instanceof MoreFragment)) {
				morefragment = new MoreFragment();
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, morefragment)
						.commit();
				// showTabContent(orderFragment);
				currentFragment = morefragment;
				current = Constants.FRAGMENT_TYPE.MORE;
			}
			switchBottomCorlor(arg0);
			break;
		default:
			if (!(currentFragment instanceof MainFragment)) {// 如果当前不是首页页面
				/*********** 如果当前是首页页面 ***********/
				mainfragment = new MainFragment();// 实例化首页页面
				fragmentManager.beginTransaction()
						.replace(R.id.chief_content_frame, mainfragment)
						.commit();// 变化之间导航，提交事务
				currentFragment = mainfragment;// 当前界面为首页
				current = Constants.FRAGMENT_TYPE.MAIN;// 当前界面标志为Constants.FRAGMENT_TYPE.MAIN
			}
			// Log.d(TAG, "The click indexnavigationIcon = " + v.getId());
			switchBottomCorlor(arg0);
			break;
		}
	}

	/**
	 * 
	 * 功能说明：底部的布局颜色的变化
	 * 
	 * @Title: switchBottomCorlor
	 * @Description: TODO
	 * @param view
	 *            当前点击的View
	 * @return void 返回类型
	 * @exception
	 * @throws
	 */
	private void switchBottomCorlor(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.app_bottom_main:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1_pressed);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_order:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2_pressed);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_my:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3_pressed);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_near:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4_pressed);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5);
			break;
		case R.id.app_bottom_more:
			bottom_main.setBackgroundResource(R.drawable.bottom_btn1);
			bottom_order.setBackgroundResource(R.drawable.bottom_btn2);
			bottom_my.setBackgroundResource(R.drawable.bottom_btn3);
			bottom_near.setBackgroundResource(R.drawable.bottom_btn4);
			bottom_more.setBackgroundResource(R.drawable.bottom_btn5_pressed);
			break;
		default:

			break;
		}
	}

}
